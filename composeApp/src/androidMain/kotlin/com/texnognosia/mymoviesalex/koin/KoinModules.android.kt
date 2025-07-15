package com.texnognosia.mymoviesalex.koin

import com.texnognosia.mymoviesalex.platform.DatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual fun platformModule() = module {
    singleOf(::DatabaseDriverFactory)
}