package com.example.moviesapp_saeedhonari.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviesapp_saeedhonari.data.model.Movie

@Dao
interface MoviewDao {


    /**
     * Insert Movies into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(Movies: List<Movie>): List<Long>

    /**
     * Get all the Movies from database
     */
    @Query("SELECT * FROM movies_list_table")
    fun getNewsMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movies_list_table")
    abstract fun deleteAllMovies()
}