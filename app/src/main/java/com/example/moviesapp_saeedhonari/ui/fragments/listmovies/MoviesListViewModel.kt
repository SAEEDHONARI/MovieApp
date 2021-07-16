package com.example.moviesapp_saeedhonari.ui.fragments.listmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp_saeedhonari.api.network.Resource
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
    val breakingNews: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()
    var ResourceMovie: Resource<List<Movie>?>? = null

    private fun GetMovies(movieTitle: String): LiveData<Resource<List<Movie>?>> {
        return movieRepository.getMovies(movieTitle)
    }

    fun getMovies(movieTitle: String) = GetMovies(movieTitle)

    /**
     * Loading Movies from internet only
     */
    private fun MoviesFromOnlyServer(movieTitle: String, page: Int) =
        movieRepository.getMoviesFromServerOnly(movieTitle, page)

    fun getMoviesFromServer(movieTitle: String, page: Int) =
        MoviesFromOnlyServer(movieTitle, page)

    fun CleareCache() = movieRepository.CleareCache()

}