package com.samples.coroutinestesting

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.samples.coroutinestesting.pojo.AllPeople
import com.samples.coroutinestesting.networking.LiveDataResult
import com.samples.coroutinestesting.platform.MainViewModelFactory
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var btn: Button
    lateinit var progressBar: ProgressBar
    val TAG = MainActivity::class.java.simpleName

    private var pageNo: Int = 1

    private val mainViewModelFactory: MainViewModelFactory =
        MainViewModelFactory(Dispatchers.Main, Dispatchers.IO)

    private val mainVM: MainActivityVM by lazy {
        ViewModelProviders.of(this, mainViewModelFactory).get(
            MainActivityVM::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainVM.mAllPeopleResponse.observe(this, this.dataObserver)
        mainVM.loadingLiveData.observe(this, this.loadingObserver)
        textView = findViewById(R.id.text1)
        progressBar = findViewById(R.id.progressBar)
        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            startRequest()
        }
    }

    fun startRequest() {

        mainVM.startFetchData(pageNo.toString())
    }

    private val dataObserver = Observer<LiveDataResult<AllPeople>> {
        // User data
        when (it?.status) {
            LiveDataResult.STATUS.ERROR -> {
                Log.d(TAG, "STATUS.ERROR ===== $it")
            }
            LiveDataResult.STATUS.SUCCESS -> {
                pageNo += 1
                Log.d(TAG, "STATUS.SUCCESS =====")
                Log.d(TAG, "data is ${it.data}")
                val allPeople = it.data as AllPeople
                textView.text = allPeople.results.toString()
            }
            LiveDataResult.STATUS.LOADING -> {
                Log.d(TAG, "STATUS.LOADING =====")
            }
        }
    }
    private val loadingObserver = Observer<Boolean> { visible ->
        // Show hide progress bar
        if (visible) {
            textView.text = ""
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
        }
    }
}


