package com.stopstone.sunflower.view.garden

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.storage.Storage

class GardenViewModel : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        val movies = Storage.movieList
        _movieList.value = movies
    }

    fun updateFavoriteStatus(movie: Movie) {
        val updateList = Storage.movieList.map {
            if (it.title == movie.title) {
                it.copy(favorite = !it.favorite)
            } else {
                it
            }
        }
        Storage.movieList.clear()
        Storage.movieList.addAll(updateList)
        _movieList.value = Storage.movieList
    }
}