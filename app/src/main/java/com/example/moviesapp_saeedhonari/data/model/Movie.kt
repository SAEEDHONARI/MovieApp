package com.example.moviesapp_saeedhonari.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "movies_list_table")
data class Movie(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("imdbID") var imdbID: String? = null,
    @SerializedName("Poster") var poster: String? = null,
    @SerializedName("Title") var title: String? = null,
    @SerializedName("Type") var type: String? = null,
    @SerializedName("Year") var year: String? = null
): Parcelable