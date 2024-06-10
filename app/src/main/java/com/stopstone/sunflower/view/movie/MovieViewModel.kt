package com.stopstone.sunflower.view.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.data.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

/* 생성자에 @Inject를 추가해여 Hilt에 결함정보 제공*/
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    fun loadMovieList() {
        repository.loadMovieList { _movieList.value = it }
    }

    fun updateFavoriteStatus(movie: Movie) {
        repository.updateFavoriteStatus(movie)
    }

}