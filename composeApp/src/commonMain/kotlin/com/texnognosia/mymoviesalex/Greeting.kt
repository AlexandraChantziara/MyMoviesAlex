package com.texnognosia.mymoviesalex

import com.texnognosia.mymoviesalex.platform.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}