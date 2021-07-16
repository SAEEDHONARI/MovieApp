package com.example.moviesapp_saeedhonari.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp_saeedhonari.data.model.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    /**
     * Get DAO's
     */
    abstract fun MoviesDao(): MoviewDao

}