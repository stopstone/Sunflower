package com.stopstone.sunflower.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
)

@Parcelize
data class Movie(
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double,
    val favorite: Boolean,
    val viewType: Int = 0,
) : Parcelable
