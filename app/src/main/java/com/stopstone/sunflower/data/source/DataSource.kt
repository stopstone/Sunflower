package com.stopstone.sunflower.data.source

import com.stopstone.sunflower.data.model.Movie
import retrofit2.Callback

interface DataSource {
    fun getMovieList(callback: (List<Movie>) -> Unit)
}