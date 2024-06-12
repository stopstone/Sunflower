package com.stopstone.sunflower.view.garden

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.data.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// GardenViewModel.kt
@HiltViewModel
class GardenViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    fun loadMovies() {
        repository.loadMovieList { movie ->
            _movieList.value = movie
        }
    }

    fun updateFavoriteStatus(movie: Movie) {
        repository.updateFavoriteStatus(movie)
    }
}