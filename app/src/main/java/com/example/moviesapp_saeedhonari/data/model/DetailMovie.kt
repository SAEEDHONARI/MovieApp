package com.example.moviesapp_saeedhonari.data.model
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "movies_detail_table")
data class DetailMovie(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("Actors")
    var actors: String?=null,
    @SerializedName("Awards")
    var awards: String?=null,
    @SerializedName("BoxOffice")
    var boxOffice: String?=null,
    @SerializedName("Country")
    var country: String?=null,
    @SerializedName("DVD")
    var dVD: String?=null,
    @SerializedName("Director")
    var director: String?=null,
    @SerializedName("Genre")
    var genre: String?=null,
    @SerializedName("imdbID")
    var imdbID: String?=null,
    @SerializedName("imdbRating")
    var imdbRating: String?=null,
    @SerializedName("imdbVotes")
    var imdbVotes: String?=null,
    @SerializedName("Language")
    var language: String?=null,
    @SerializedName("Metascore")
    var metascore: String?=null,
    @SerializedName("Plot")
    var plot: String?=null,
    @SerializedName("Poster")
    var poster: String?=null,
    @SerializedName("Production")
    var production: String?=null,
    @SerializedName("Rated")
    var rated: String?=null,
    @Embedded
    @SerializedName("Ratings")
    @Ignore var ratings: List<Ratings>?,
    @SerializedName("Released")
    var released: String?=null,
    @SerializedName("Response")
    var response: String?=null,
    @SerializedName("Runtime")
    var runtime: String?=null,
    @SerializedName("Title")
    var title: String?=null,
    @SerializedName("Type")
    var type: String?=null,
    @SerializedName("Website")
    var website: String?=null,
    @SerializedName("Writer")
    var writer: String?=null,
    @SerializedName("Year")
    var year: String?=null
): Parcelable
{
    constructor() : this(0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", emptyList(), "", "", "")
}

    @Parcelize
    @Entity(tableName = "movies_rating_table")
    data class Ratings(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @SerializedName("Value")
        var value: String? = null,
        @SerializedName("Source")
        var source: String? = null
    ): Parcelable
