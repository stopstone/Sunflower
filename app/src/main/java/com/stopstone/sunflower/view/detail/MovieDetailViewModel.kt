package com.stopstone.sunflower.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.data.repository.detail.MovieDetailRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieDetailRepositoryImpl
) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun setMovie(movieId: Int) {
        viewModelScope.launch {
            val fetchedMovie = repository.getMovieById(movieId).value
            fetchedMovie?.let {
                _movie.value = it
            }
        }
    }

    fun toggleFavorite() {
        val updatedMovie = _movie.value?.copy(favorite = !(_movie.value?.favorite ?: false))
        updatedMovie?.let { newMovie ->
            _movie.value = newMovie
            viewModelScope.launch {
                repository.updateMovie(newMovie)
            }
        }
    }
}