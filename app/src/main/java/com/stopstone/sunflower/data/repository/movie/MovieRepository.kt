package com.stopstone.sunflower.data.repository.movie

import com.stopstone.sunflower.data.model.Movie

interface MovieRepository {
    fun loadMovieList(callback: (List<Movie>) -> Unit)
    fun updateFavoriteStatus(movie: Movie)
}
