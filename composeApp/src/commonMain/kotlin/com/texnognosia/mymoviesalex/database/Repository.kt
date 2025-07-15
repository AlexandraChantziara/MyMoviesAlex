package com.texnognosia.mymoviesalex.database

import com.texnognosia.mymoviesalex.dataclasses.MovieInformation

class Repository(private val dao: DatabaseDao) {

    suspend fun upsertMyMovies(myMoviesData : MovieInformation){
        dao.upsertMyMovies(myMoviesData)
    }

    suspend fun deleteMyMovies(myMoviesData: MovieInformation){
        dao.deleteMyMovies(myMoviesData)
    }

    fun getAllMyMovies() = dao.getAllMyMovies()
}