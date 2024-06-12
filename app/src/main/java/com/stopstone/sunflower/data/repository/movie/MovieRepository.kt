package com.stopstone.sunflower.data.repository.movie

import androidx.lifecycle.LiveData
import com.stopstone.sunflower.data.model.Movie

interface MovieRepository {
    val isLocalSource: Boolean
    fun loadMovieList(callback: (List<Movie>) -> Unit)
    fun updateFavoriteStatus(movie: Movie)
    fun getMovieById(movieId: Int): LiveData<Movie>
    fun updateMovie(movie: Movie)
}