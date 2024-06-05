package com.stopstone.sunflower.data.repository.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.storage.Storage
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val storage: Storage
) : MovieDetailRepository {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        _movies.value = storage.movieList
    }

    override fun getMovieById(movieId: Int): LiveData<Movie> {
        val movie = _movies.value?.find { it.id == movieId }
        return MutableLiveData(movie)
    }

    override fun updateMovie(movie: Movie) {
        val index = storage.movieList.indexOfFirst { it.id == movie.id }
        if (index != -1) {
            storage.movieList[index] = movie
        }
    }
}