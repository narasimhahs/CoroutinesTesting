package com.samples.coroutinestesting.di

import com.samples.coroutinestesting.repo.MainActivityRepo
import org.koin.dsl.module

val RepoDependency = module {

    factory {
        MainActivityRepo()
    }

}