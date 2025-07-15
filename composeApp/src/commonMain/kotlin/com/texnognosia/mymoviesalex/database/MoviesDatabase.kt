package com.texnognosia.mymoviesalex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.texnognosia.mymoviesalex.dataclasses.MovieInformation

@Database(
    entities = [
        MovieInformation::class
    ],
    version = 1
)

@TypeConverters(TypeAdapters::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun dao() : DatabaseDao
}