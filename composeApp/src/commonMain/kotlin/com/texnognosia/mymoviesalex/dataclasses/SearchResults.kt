package com.texnognosia.mymoviesalex.dataclasses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResults(
    @SerialName("Search")
    val search : List<SearchResult>,
    val totalResults : String,
    @SerialName("Response")
    val response : String
)

@Serializable
data class SearchResult(
    @SerialName("Title")
    val title : String,
    @SerialName("Year")
    val year : String,
    val imdbID : String,
    @SerialName("Type")
    val type : String,
    @SerialName("Poster")
    val poster : String,
)

@Serializable
data class MovieInformation(
    @SerialName("Title")
    val title : String,
    @SerialName("Year")
    val year : String,
    @SerialName("Rated")
    val rated : String,
    @SerialName("Released")
    val released : String,
    @SerialName("Runtime")
    val runtime : String,
    @SerialName("Genre")
    val genre : String,
    @SerialName("Director")
    val director : String,
    @SerialName("Writer")
    val writer : String,
    @SerialName("Actors")
    val actors : String,
    @SerialName("Plot")
    val plot : String,
    @SerialName("Language")
    val language : String,
    @SerialName("Country")
    val country : String,
    @SerialName("Awards")
    val awards : String,
    @SerialName("Poster")
    val poster : String,
    @SerialName("Ratings")
    val ratings : List<Ratings>,
    @SerialName("Metascore")
    val metascore : String,
    val imdbRating : String,
    val imdbVotes : String,
    val imdbID : String,
    @SerialName("Type")
    val type : String,
    @SerialName("DVD")
    val dvd : String,
    @SerialName("BoxOffice")
    val boxOffice : String,
    @SerialName("Production")
    val production : String,
    @SerialName("Website")
    val website : String,
    @SerialName("Response")
    val response : String
)

@Serializable
data class Ratings (
    @SerialName("Source")
    val source : String,
    @SerialName("Value")
    val value : String
)