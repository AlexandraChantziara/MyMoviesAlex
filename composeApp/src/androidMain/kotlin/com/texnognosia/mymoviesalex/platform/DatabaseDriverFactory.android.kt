package com.texnognosia.mymoviesalex.platform

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.texnognosia.mymoviesalex.database.MoviesDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory(private val context: Context) {
    actual fun getDatabase() : MoviesDatabase {
        val dbFile = context.getDatabasePath(databaseName)
        return Room.databaseBuilder<MoviesDatabase>(
            context,
            dbFile.absolutePath
        )
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .build()

    }
}