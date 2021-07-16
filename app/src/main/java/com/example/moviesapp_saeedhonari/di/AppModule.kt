package com.example.moviesapp_saeedhonari.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp_saeedhonari.data.local.MoviewDao
import com.example.moviesapp_saeedhonari.data.local.MovieDatabase
import com.example.moviesapp_saeedhonari.data.remote.MovieApi
import com.example.moviesapp_saeedhonari.data.remote.network.LiveDataCallAdapterFactoryForRetrofit
import com.example.moviesapp_saeedhonari.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * Provides ApiServices client for Retrofit
     */
    @Singleton
    @Provides
    fun provideNewsService(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(MovieApi::class.java)
    }


    /**
     * Provides app AppDatabase
     */
    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "news-db")
            .fallbackToDestructiveMigration().build()


    /**
     * Provides MoviesDao an object to access Movies table from Database
     */
    @Singleton
    @Provides
    fun provideUserDao(db: MovieDatabase): MoviewDao = db.MoviesDao()


}
