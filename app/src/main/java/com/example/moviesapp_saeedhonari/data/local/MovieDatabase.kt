package com.example.moviesapp_saeedhonari.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp_saeedhonari.data.local.detail.DetailDao
import com.example.moviesapp_saeedhonari.data.local.list.MovieDao
import com.example.moviesapp_saeedhonari.data.model.DetailMovie
import com.example.moviesapp_saeedhonari.data.model.Movie
import com.example.moviesapp_saeedhonari.data.model.Ratings


@Database(entities = [Movie::class,DetailMovie::class,Ratings::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    /**
     * Get DAO's
     */
    abstract fun MoviesDao(): MovieDao
    abstract fun DetailDao(): DetailDao

}