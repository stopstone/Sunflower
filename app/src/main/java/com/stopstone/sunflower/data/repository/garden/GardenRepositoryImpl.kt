package com.stopstone.sunflower.data.repository.garden

import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.storage.Storage
import javax.inject.Inject

class GardenRepositoryImpl @Inject constructor(private val storage: Storage) : GardenRepository {
    override fun getMovieList(): List<Movie> {
        return storage.movieList
    }

    override fun updateFavoriteStatus(movie: Movie) {
        val updateList = storage.movieList.map {
            if (it.id == movie.id) {
                it.copy(favorite = !it.favorite)
            } else {
                it
            }
        }
        storage.movieList.clear()
        storage.movieList.addAll(updateList)
    }
}