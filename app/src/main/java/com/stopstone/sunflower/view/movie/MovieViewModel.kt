package com.stopstone.sunflower.view.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stopstone.sunflower.data.repository.movie.MovieRepositoryImpl
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.storage.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepositoryImpl) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    fun loadMovieList() {
        repository.loadMovieList { movies ->
            _movieList.value = movies
        }
    }

    fun updateFavoriteStatus(movie: Movie) {
        repository.updateFavoriteStatus(movie)
        _movieList.value = Storage.movieList
    }
}