package com.stopstone.sunflower.presenter

import android.content.Context
import android.content.Intent
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.data.MovieResponse
import com.stopstone.sunflower.data.TMDBConnection
import com.stopstone.sunflower.storage.Storage
import com.stopstone.sunflower.view.MovieDetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(private val movieView: MovieContract.MovieView, private val storage: Storage) : MovieContract.MoviePresenter {
    private var startPage = 1
    // 현재는 스크롤시에면 presenter에서 데이터를 받기 때문에 2페이지부터 받는다.

    override fun loadMovieList() {
        // API 호출
        TMDBConnection.movieService.getPopularMovies(startPage++)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response.body()?.results?.let {
                        Storage.movieList.addAll(it)
                        movieView.showMovieList(Storage.movieList)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    override fun loadMovieDetailPage(movie: Movie, context: Context?) {
        // it == movie를 하면 copy로 인한 메모리 값이 변경되기 때문에 title이 같은 목록을 찾는다.
        // title이 중복된 영화가 있을 수 있으므로 추후 id로 수정할 것
        val movieDetail = storage.movieList.find { it.title === movie.title } ?: return
        movieView.showMovieDetailPage(movieDetail)
    }
}