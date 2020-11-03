package com.samples.coroutinestesting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samples.coroutinestesting.networking.LiveDataResult
import com.samples.coroutinestesting.usecases.UseCases
import com.samples.coroutinestesting.pojo.AllPeople
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

class MainActivityVM(
    mainDispacher : CoroutineDispatcher,
    ioDispacher : CoroutineDispatcher,
    val useCases: UseCases
    ) : ViewModel(),KoinComponent {

    var mAllPeopleResponse = MutableLiveData<LiveDataResult<AllPeople>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = MainActivityVM::class.java.simpleName
    private val job = SupervisorJob()
    val uiScope = CoroutineScope(mainDispacher + job)
    val ioScope = CoroutineScope(ioDispacher + job)

    fun startFetchData(param:String){
        uiScope.launch {
            mAllPeopleResponse.value = LiveDataResult.loading()
            setLoadingVisibility(true)
            try {
                val data = ioScope.async {
                    return@async useCases.fetchData(param)
                }.await()
                try {
                    mAllPeopleResponse.value = LiveDataResult.success(data)
                } catch (e: Exception) {
                    println("error 11111 = $e")
                }
                setLoadingVisibility(false)
            } catch (e: Exception) {
                setLoadingVisibility(false)
                println("error 2222 = $e")
                mAllPeopleResponse.value = LiveDataResult.error(e)
            }
        }
    }

    private fun setLoadingVisibility(visible: Boolean) {
        loadingLiveData.postValue(visible)
    }

    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}