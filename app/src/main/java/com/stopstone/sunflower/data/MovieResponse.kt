package com.stopstone.sunflower.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
)

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    val favorite: Boolean,
    val viewType: Int = 0,
) : Parcelable
