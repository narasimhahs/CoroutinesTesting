package com.samples.coroutinestesting.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samples.coroutinestesting.MainActivityVM
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.KoinComponent
import org.koin.core.get

class MainViewModelFactory constructor(
    private val mainDispather: CoroutineDispatcher,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory, KoinComponent {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainActivityVM::class.java)) {
            MainActivityVM(mainDispather, ioDispatcher, get()) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}