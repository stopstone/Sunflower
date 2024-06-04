package com.stopstone.sunflower.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.storage.Storage

class MovieDetailViewModel : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun setMovie(movie: Movie) {
        _movie.value = movie
    }

    fun toggleFavorite() {
        val updatedMovie = _movie.value?.copy(favorite = !(_movie.value?.favorite ?: false))
        updatedMovie?.let { newMovie ->
            _movie.value = newMovie
            updateMovieInStorage(newMovie)
        }
    }

    private fun updateMovieInStorage(movie: Movie) {
        val index = Storage.movieList.indexOfFirst { it.title == movie.title }
        if (index != -1) {
            Storage.movieList[index] = movie
        }
    }
}