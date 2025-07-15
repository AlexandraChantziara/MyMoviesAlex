package com.texnognosia.mymoviesalex.platform

import java.io.File

val appDir: File
    get() {
        val os = System.getProperty("os.name").lowercase()
        return when {
            os.contains("win") -> {
                File(System.getenv("AppData"), "Alex/Movies/db")
            }

            os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
                File(System.getProperty("user.home"), ".Alex.Wishlist")
            }

            os.contains("mac") -> {
                File(System.getProperty("user.home"), "Library/Application Support/Alex/Wishlist")
            }

            else -> error("Unsupported operating system")
        }
    }
