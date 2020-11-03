package com.samples.coroutinestesting.di

import com.samples.coroutinestesting.usecases.UseCases
import org.koin.dsl.module

val useCaseDependency = module {

    factory {
        UseCases()
    }

}