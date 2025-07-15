package com.texnognosia.mymoviesalex.platform

import com.texnognosia.mymoviesalex.database.MoviesDatabase

const val databaseName = "Movies.db"


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DatabaseDriverFactory {
    fun getDatabase() :MoviesDatabase
}

fun createDatabase(databaseDriverFactory: DatabaseDriverFactory) = databaseDriverFactory.getDatabase()

fun getDao(database:MoviesDatabase) = database.dao()