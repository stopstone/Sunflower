package com.stopstone.sunflower.storage

import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.data.MovieResponse
import com.stopstone.sunflower.data.TMDBConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MovieStorage {
    private var startPage = 1
    var movieList: MutableList<Movie> = mutableListOf()

    fun loadMovies(callback: ((List<Movie>) -> Unit)?)     {
        // API 호출
        TMDBConnection.getService().getPopularMovies(startPage).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.results?.let {
                    movieList.addAll(it)
                    startPage++ // 페이지 번호 증가
                    callback?.let { callback -> callback(movieList) }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    /*
    * 좋아요 버튼이 눌리면 호출되는 함수
    * 좋아요 눌린 아이템과 같은 식물의 좋아요 상태를 변경하여 copy를 통해 저장한다.
    * */

    fun updateFavoriteStatus(movie: Movie) {
        movieList = movieList.map {
            if (it.title == movie.title)
                it.copy(favorite = !it.favorite)
            else {
                it
            }
        }.toMutableList()
    }
}
