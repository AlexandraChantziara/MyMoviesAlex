package com.texnognosia.mymoviesalex.koin

import com.texnognosia.mymoviesalex.MainViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun commonModule() = module {
/*    singleOf(::createDatabase)
    singleOf(::getDao)
    singleOf(::Repository)*/
    singleOf(::MainViewModel)
}

expect fun platformModule() : Module