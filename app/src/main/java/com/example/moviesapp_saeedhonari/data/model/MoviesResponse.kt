package com.example.moviesapp_saeedhonari.data.model

data class MoviesResponse(
    val Movies: MutableList<Movie>,
    val status: String,
    val totalResults: Int
)