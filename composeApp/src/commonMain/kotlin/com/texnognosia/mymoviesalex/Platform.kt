package com.texnognosia.mymoviesalex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform