package com.texnognosia.mymoviesalex.platform

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.texnognosia.mymoviesalex.database.MoviesDatabase
import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual fun getDatabase() : MoviesDatabase {
        val dbFile = databaseFile
        return Room.databaseBuilder<MoviesDatabase>(dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    private val databaseFile: File
        get() = File(appDir.also { if (!it.exists()) it.mkdirs() }, databaseName)
}