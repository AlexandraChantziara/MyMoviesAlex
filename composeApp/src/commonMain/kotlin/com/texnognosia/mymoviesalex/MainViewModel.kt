package com.texnognosia.mymoviesalex

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.texnognosia.mymoviesalex.dataclasses.SearchResult

class MainViewModel : ViewModel() {
    var loading by mutableStateOf(false)
    val resultList = mutableStateListOf<SearchResult>()
}