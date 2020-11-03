package com.samples.coroutinestesting.networking

import com.samples.coroutinestesting.pojo.AllPeople
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginAPIService {

    @GET("people/")
    suspend fun getLoginData(@Query("page") page:String): AllPeople
}