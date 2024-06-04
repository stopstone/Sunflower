package com.stopstone.sunflower.view.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.data.MovieResponse
import com.stopstone.sunflower.data.TMDBConnection
import com.stopstone.sunflower.storage.Storage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> = _movieList

    private var startPage = 1

    fun loadMovieList() {
        // API 호출
        TMDBConnection.movieService.getPopularMovies(startPage++)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response.body()?.results?.let {
                        Storage.movieList.addAll(it)
                        _movieList.value = Storage.movieList
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    fun updateFavoriteStatus(movie: Movie) {
        val updateList = Storage.movieList.map {
            if (it.id == movie.id) {
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