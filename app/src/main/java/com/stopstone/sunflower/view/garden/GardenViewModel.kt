package com.stopstone.sunflower.view.garden

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.data.repository.garden.GardenRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// GardenViewModel.kt
@HiltViewModel
class GardenViewModel @Inject constructor (private val movieRepository: GardenRepositoryImpl) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    fun fetchMovies() {
        val movies = movieRepository.getMovieList()
        _movieList.value = movies
    }

    fun updateFavoriteStatus(movie: Movie) {
        movieRepository.updateFavoriteStatus(movie)
        _movieList.value = movieRepository.getMovieList()
    }
}