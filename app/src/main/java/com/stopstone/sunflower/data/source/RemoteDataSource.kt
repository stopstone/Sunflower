package com.stopstone.sunflower.data.source

import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.data.model.MovieResponse
import com.stopstone.sunflower.data.remote.TMDBService
import com.stopstone.sunflower.storage.Storage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val tMDBService: TMDBService,
    private val storage: Storage
) : DataSource {
    private var startPage = 1

    override fun getMovieList(callback: (List<Movie>) -> Unit) {
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
}