package com.texnognosia.mymoviesalex.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform