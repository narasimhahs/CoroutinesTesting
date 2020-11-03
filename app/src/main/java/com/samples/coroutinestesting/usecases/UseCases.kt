package com.samples.coroutinestesting.usecases

import com.samples.coroutinestesting.repo.MainActivityRepo
import com.samples.coroutinestesting.pojo.AllPeople
import org.koin.core.KoinComponent
import org.koin.core.inject

class UseCases : KoinComponent {

    val repo: MainActivityRepo by inject()

    suspend fun fetchData(parameter:String) : AllPeople {
        return repo.fetchData(parameter)
    }
}