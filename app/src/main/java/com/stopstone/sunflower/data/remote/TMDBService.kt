package com.stopstone.sunflower.data.remote

import com.stopstone.sunflower.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") pageNum: Int
    ): Call<MovieResponse>
}
