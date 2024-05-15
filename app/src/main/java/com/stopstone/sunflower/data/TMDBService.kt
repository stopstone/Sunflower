package com.stopstone.sunflower.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") pageNum: Int,
        @Query("api_key") apiKey: String = "05869e92f9341da5763cfd723ec46182",
    ): Call<MovieResponse>
}
