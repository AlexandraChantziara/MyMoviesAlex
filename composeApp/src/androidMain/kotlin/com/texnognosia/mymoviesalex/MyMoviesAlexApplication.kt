package com.texnognosia.mymoviesalex

import android.app.Application
import com.texnognosia.mymoviesalex.koin.KoinInit
import org.koin.android.ext.koin.androidContext

class MyMoviesAlexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInit().init {
            androidContext(this@MyMoviesAlexApplication)
        }
    }
}