package com.example.moviesapp_saeedhonari.data.remote

import androidx.lifecycle.LiveData
import com.example.moviesapp_saeedhonari.api.network.Resource
import com.example.moviesapp_saeedhonari.data.model.DetailMovie
import com.example.moviesapp_saeedhonari.data.model.ListMovies
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApi {
    @GET(".")
    fun getMoviesList(
        @QueryMap options: Map<String, String>,
        @Query("s") pageNumber: String ="batman",
        @Query("page") page: Int = 1,
        ): LiveData<Resource<ListMovies>>


    @GET(".")
    fun getMovieDetail(
        @QueryMap options: Map<String, String>,
        @Query("i") imdbID: String ="0",
        ): LiveData<Resource<DetailMovie>>

}
