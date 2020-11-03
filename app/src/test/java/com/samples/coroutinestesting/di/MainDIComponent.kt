package com.samples.coroutinestesting.di


fun configureTestAppComponent(baseApi: String)
    = listOf(UseCaseDependency,MockWebServerDependency,configureNetworkModuleForTest(baseApi),RepoDependency)
