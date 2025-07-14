package com.texnognosia.mymoviesalex

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.texnognosia.mymoviesalex.dataclasses.MovieInformation
import com.texnognosia.mymoviesalex.dataclasses.SearchResult

class MainViewModel : ViewModel() {
    var loading by mutableStateOf(false)
    val resultList = mutableStateListOf<SearchResult>()
    var movieInformation by mutableStateOf<MovieInformation?>(null)
    var isClicked by mutableStateOf(false)
    var currentRoute by mutableStateOf("")
}