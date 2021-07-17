package com.example.moviesapp_saeedhonari.data.local.detail

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesapp_saeedhonari.data.model.DetailMovie

@Dao
interface DetailDao {

    /**
     * Insert Movies into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovies(Movies: DetailMovie)

    /**
     * Get all the Movies from database
     */
    @Query("SELECT * FROM movies_detail_table WHERE imdbID = :idm")
    fun getDetailMovie(idm:String): LiveData<DetailMovie>

    @Query("DELETE FROM movies_detail_table WHERE imdbID= :idm")
    fun deleteDetailMovie(idm:String)
}