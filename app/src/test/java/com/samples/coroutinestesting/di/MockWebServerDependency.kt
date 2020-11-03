package com.samples.coroutinestesting.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val MockWebServerDependency = module {

    factory {
        MockWebServer()
    }

}