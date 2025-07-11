package com.texnognosia.mymoviesalex.koin

import org.koin.core.Koin
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.KoinAppDeclaration

class KoinInit {
    fun init(appDeclaration: KoinAppDeclaration = {}): Koin {
        return startKoin {
            appDeclaration()
            modules(
                listOf(
                    platformModule(),
                    commonModule(),
                ),
            )
        }.koin
    }
}