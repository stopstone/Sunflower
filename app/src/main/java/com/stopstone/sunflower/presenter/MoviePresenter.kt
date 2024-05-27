package com.stopstone.sunflower.presenter

import com.stopstone.sunflower.data.MovieResponse
import com.stopstone.sunflower.data.TMDBConnection
import com.stopstone.sunflower.storage.MovieStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val movieView: MovieContract.MovieView) : MovieContract.MoviePresenter {
    private var startPage = 1
    // 현재는 스크롤시에면 presenter에서 데이터를 받기 때문에 2페이지부터 받는다.

    override fun loadMovieList() {
        // API 호출
        TMDBConnection.movieService.getPopularMovies(++startPage)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response.body()?.results?.let {
                        MovieStorage.movieList.addAll(it)
                        movieView.showMovieList(MovieStorage.movieList)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}