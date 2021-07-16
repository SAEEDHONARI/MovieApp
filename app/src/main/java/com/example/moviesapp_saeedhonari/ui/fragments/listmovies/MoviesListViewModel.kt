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
    var ResourceArticle: Resource<List<Movie>?>? = null

    private fun moviesList(titleMovie: String): LiveData<Resource<List<Movie>?>> {
        MoviePage++
        return movieRepository.getMovies(titleMovie, MoviePage)
    }

    fun getMoviesList(titleMovie: String) = moviesList(titleMovie)

    /**
     * Loading news articles from internet only
     */
    private fun newsArticlesFromOnlyServer(titleMovie: String, page: Int) =
        movieRepository.getMoviesFromServerOnly(titleMovie, page)

    fun getNewsArticlesFromServer(titleMovie: String, page: Int) =
        newsArticlesFromOnlyServer(titleMovie, page)

    fun CleareCache() = movieRepository.CleareCache()

}