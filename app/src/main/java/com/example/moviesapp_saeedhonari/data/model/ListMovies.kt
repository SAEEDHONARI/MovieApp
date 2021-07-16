package com.example.moviesapp_saeedhonari.data.model


import com.google.gson.annotations.SerializedName

data class ListMovies(
    @SerializedName("Response") val response: String ="",
    @SerializedName("Search") val search: List<Movie> = emptyList(),
    @SerializedName("totalResults") val totalResults: String =""
)