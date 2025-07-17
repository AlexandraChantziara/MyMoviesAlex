package com.texnognosia.mymoviesalex

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.texnognosia.mymoviesalex.database.Repository
import com.texnognosia.mymoviesalex.dataclasses.MovieInformation
import com.texnognosia.mymoviesalex.dataclasses.SearchResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    var loading by mutableStateOf(false)
    val resultList = mutableStateListOf<SearchResult>()
    var movieInformation by mutableStateOf<MovieInformation?>(null)
    var isClicked by mutableStateOf(false)
    var currentRoute by mutableStateOf("")
    var itemToDelete by mutableStateOf<MovieInformation?>(null)

    fun upsertMyMovies(myMoviesData : MovieInformation){
        viewModelScope.launch {
            repository.upsertMyMovies(myMoviesData)
        }
    }

    fun deleteMyMovies(myMoviesData: MovieInformation){
        viewModelScope.launch {
            repository.deleteMyMovies(myMoviesData)
        }
    }

    val myMoviesData = repository.getAllMyMovies().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}