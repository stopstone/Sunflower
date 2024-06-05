package com.stopstone.sunflower.data.repository.garden

import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.storage.Storage

// MovieRepository.kt
interface GardenRepository {
    fun getMovieList(): List<Movie>
    fun updateFavoriteStatus(movie: Movie)
}