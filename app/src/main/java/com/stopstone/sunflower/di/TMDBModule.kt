package com.stopstone.sunflower.di

import com.stopstone.sunflower.data.remote.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TMDBConnection {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideTMDBService(retrofit: Retrofit): TMDBService {
        return retrofit.create(TMDBService::class.java)
    }
}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val requestWithHeaders = originalRequest.newBuilder()
            .url(url)
            .header("Authorization", "Bearer $ACCESS_TOKEN")
            .header("Accept", "application/json")
            .build()

        return chain.proceed(requestWithHeaders)
    }

    companion object {
        const val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNTg2OWU5MmY5MzQxZGE1NzYzY2ZkNzIzZWM0NjE4MiIsInN1YiI6IjYzYzAwMmY4MjNiZTQ2MDA4OTJjYjQ3MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.eQGm2f9ozjvxsIjDoMhhfHJrCt_D2dzxdOQ2Zl5Fts4"
        const val API_KEY = "05869e92f9341da5763cfd723ec46182"
    }
}