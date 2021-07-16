package com.example.moviesapp_saeedhonari.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.moviesapp_saeedhonari.api.network.NetworkAndDBBoundResource
import com.example.moviesapp_saeedhonari.api.network.NetworkResource
import com.example.moviesapp_saeedhonari.api.network.Resource
import com.example.moviesapp_saeedhonari.app.AppExecutors
import com.example.moviesapp_saeedhonari.data.local.MoviewDao
import com.example.moviesapp_saeedhonari.data.model.ListMovies
import com.example.moviesapp_saeedhonari.data.model.Movie
import com.example.moviesapp_saeedhonari.data.remote.MovieApi
import com.example.moviesapp_saeedhonari.util.API_KEY
import com.example.moviesapp_saeedhonari.util.ConnectivityUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton



/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source as the single source of truth.
 *
 */

@Singleton
class MovieRepository @Inject constructor(
    private val moviewDao: MoviewDao,
    private val apiServices: MovieApi,
    @ApplicationContext val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {

    /**
     * Fetch the news Movies from database if exist else fetch from web
     * and persist them in the database
     */
    fun getMovies(SearchTitle: String, page:Int=1): LiveData<Resource<List<Movie>?>> {
        val data = HashMap<String, String>()
        data["apikey"] = API_KEY
        return object : NetworkAndDBBoundResource<List<Movie>, ListMovies>(appExecutors) {
            override fun saveCallResult(item: ListMovies) {
                if (item.search.isNotEmpty()) {
                    if(page==1)
                    moviewDao.deleteAllMovies()
                    moviewDao.insertMovies(item.search)
                }
            }

            override fun shouldFetch(data: List<Movie>?) =
                (ConnectivityUtil.isConnected(context))

            override fun loadFromDb() = moviewDao.getNewsMovies()

            override fun createCall() =
                apiServices.getMoviesList(data,SearchTitle,page)

        }.asLiveData()
    }

    /**
     * Fetch the news Movies from database if exist else fetch from web
     * and persist them in the database
     * LiveData<ReListMovies<ListMovies>>
     */
    fun getMoviesFromServerOnly(SearchTitle: String, page : Int):
            LiveData<Resource<ListMovies>> {

        val data = HashMap<String, String>()
        data["apikey"] = API_KEY
        return object : NetworkResource<ListMovies>() {
            override fun createCall(): LiveData<Resource<ListMovies>> {
                return apiServices.getMoviesList(data,SearchTitle,page)
            }

        }.asLiveData()
    }

    fun CleareCache() {
        moviewDao.deleteAllMovies()
    }

}