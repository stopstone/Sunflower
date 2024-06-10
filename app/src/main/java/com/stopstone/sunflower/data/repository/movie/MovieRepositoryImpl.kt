package com.stopstone.sunflower.data.repository.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.data.model.MovieResponse
import com.stopstone.sunflower.data.remote.TMDBService
import com.stopstone.sunflower.storage.Storage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val tMDBService: TMDBService, private val storage: Storage):
    MovieRepository {
    private var startPage = 1
    private var callback: ((List<Movie>) -> Unit)? = null

    override fun loadMovieList(callback: (List<Movie>) -> Unit) {
        this.callback = callback
        tMDBService.getPopularMovies(startPage++)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response.body()?.results?.let {
                        storage.movieList.addAll(it)
                        callback(storage.movieList)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
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
        callback?.invoke(storage.movieList.toList())
    }

    override fun getMovieById(movieId: Int): LiveData<Movie> {
        val movie = storage.movieList.find { it.id == movieId }
        return MutableLiveData(movie)
    }

    override fun updateMovie(movie: Movie) {
        val index = storage.movieList.indexOfFirst { it.id == movie.id }
        if (index != -1) {
            storage.movieList[index] = movie
            callback?.invoke(storage.movieList.toList())
        }
    }
}