package com.samples.coroutinestesting.repo

import com.samples.coroutinestesting.networking.LoginAPIService
import com.aai.digisky.models.login.AllPeople
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityRepo : KoinComponent{

    val mAPIService : LoginAPIService by inject()

    suspend fun fetchData(parameter:String) : AllPeople {
        return  processDataFetchLogic(parameter)
    }

    suspend fun processDataFetchLogic(parameter:String): AllPeople{

        for (x in 0 until 10){
            println(" Data manipulation prior to REST API request if required $x")
        }

        var dataReceived = mAPIService.getLoginData(parameter)

        for (x in 0 until 5){
            println(" Data manipulation post REST API request if required $x")
        }

        return dataReceived
    }
}