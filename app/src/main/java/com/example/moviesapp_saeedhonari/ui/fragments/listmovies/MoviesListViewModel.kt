package com.example.moviesapp_saeedhonari.ui.fragments.listmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp_saeedhonari.api.network.Resource
import com.example.moviesapp_saeedhonari.data.model.DetailMovie
import com.example.moviesapp_saeedhonari.data.model.Movie
import com.example.moviesapp_saeedhonari.data.model.MoviesResponse
import com.example.moviesapp_saeedhonari.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    /**
     * Loading Movies from internet and database
     */
    var MoviePage = 1

    //Get List Of Movies

    private fun moviesList(titleMovie: String): LiveData<Resource<List<Movie>?>> {
        MoviePage++
        return movieRepository.getMovies(titleMovie, MoviePage)
    }

    fun getMoviesList(titleMovie: String) = moviesList(titleMovie)

    /**
     * Loading news articles from internet only
     */
    private fun newsArticlesFromOnlyServer(titleMovie: String, page: Int) = movieRepository.getMoviesFromServerOnly(titleMovie, page)

    fun getNewsArticlesFromServer(titleMovie: String, page: Int) = newsArticlesFromOnlyServer(titleMovie, page)



    //Get Detail Of Movie

    private fun detailMovie(imdbid: String): LiveData<Resource<DetailMovie?>> {
        return movieRepository.getDetailMovie(imdbid)
    }

    fun getDetailMovie(imdbid: String) = detailMovie(imdbid)

    /**
     * Loading news articles from internet only
     */
    private fun detailMovieFromOnlyServer(imdbid: String) = movieRepository.getDetailMoviesFromServerOnly(imdbid)


    //Cleare Casche
    fun CleareCache() = movieRepository.CleareCache()

}