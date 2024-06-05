package com.stopstone.sunflower.data.repository.detail

import androidx.lifecycle.LiveData
import com.stopstone.sunflower.data.model.Movie

interface MovieDetailRepository {
    fun getMovieById(movieId: Int): LiveData<Movie>
    fun updateMovie(movie: Movie)
}