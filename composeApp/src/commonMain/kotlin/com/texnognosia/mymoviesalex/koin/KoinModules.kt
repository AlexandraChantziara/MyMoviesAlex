package com.texnognosia.mymoviesalex.koin

import com.texnognosia.mymoviesalex.MainViewModel
import com.texnognosia.mymoviesalex.database.Repository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.texnognosia.mymoviesalex.platform.getDao
import com.texnognosia.mymoviesalex.platform.createDatabase


fun commonModule() = module {
    singleOf(::createDatabase)
    singleOf(::getDao)
    singleOf(::Repository)
    singleOf(::MainViewModel)
}

expect fun platformModule() : Module