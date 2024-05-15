package com.stopstone.sunflower.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TMDBConnection {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private val instance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getService(): TMDBService {
            return instance.create(TMDBService::class.java)
        }
    }
}