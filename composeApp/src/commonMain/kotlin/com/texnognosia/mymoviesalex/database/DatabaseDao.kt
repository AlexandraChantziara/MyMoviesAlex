package com.texnognosia.mymoviesalex.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.texnognosia.mymoviesalex.dataclasses.MovieInformation
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {
    @Upsert
    suspend fun upsertMyMovies(myMoviesData : MovieInformation)

    @Delete
    suspend fun deleteMyMovies(myMoviesData: MovieInformation)

    @Query("SELECT * FROM MovieInformation")
    fun getAllMyMovies() : Flow<List<MovieInformation>>
}