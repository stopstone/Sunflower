package com.stopstone.sunflower.data

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TMDBConnection private constructor() {
    private val retrofit: Retrofit
    init {
        Log.d("TMDBConnection", "TMDBConnection instance created.")
        // 앱을 실행할 때 단 한 번 로그가 출력됩니다.
        // -> 단 한 번 생성된다는 뜻입니다.
        // 화면 전환 후 복귀해도 다시 생성되지 않습니다.
    }

    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getService(): TMDBService {
        // instance가 처음 접근될 때 객체를 생성하고,
        // getService()에서 TMDBService를 반환합니다.
        return retrofit.create(TMDBService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        // TMDBConnection이 처음 접근될 때 한 번 생성됩니다.
        val instance: TMDBConnection by lazy {
            TMDBConnection()
        }
    }
}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithHeaders = originalRequest.newBuilder()
            .header("Authorization", "Bearer $ACCESS_TOKEN") // 필요 시 추가
            .header("Accept", "application/json")
            .header("api_key", API_KEY) // API 키 추가
            .build()
        return chain.proceed(requestWithHeaders)
    }

    companion object {
        const val ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNTg2OWU5MmY5MzQxZGE1NzYzY2ZkNzIzZWM0NjE4MiIsInN1YiI6IjYzYzAwMmY4MjNiZTQ2MDA4OTJjYjQ3MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.eQGm2f9ozjvxsIjDoMhhfHJrCt_D2dzxdOQ2Zl5Fts4"
        const val API_KEY = "05869e92f9341da5763cfd723ec46182"
    }
}

/* 개선된 코드 설명:
 * - 생성자를 비공개로 변경하여 외부에서 인스턴스를 생성하지 못하도록 했습니다.
 * - lazy를 사용하여 TMDBConnection 객체를 초기화했습니다.
 * - MovieStorage 코드에서 TMDBConnection.getService()에 접근할 때,
 *   instance가 처음 접근될 때 객체가 생성되고, getService()에서 TMDBService를 반환합니다.
 */

/* lazy를 사용하지 않을 경우의 예:
 * var instance: Retrofit? = null
 * if (instance == null) {
 *     instance = Retrofit.Builder()
 *         .baseUrl(BASE_URL)
 *         .addConverterFactory(GsonConverterFactory.create())
 *         .build()
 * }
 * return instance!!
 *
 * 이 경우 instance 변수를 var로 선언 후 null 체크를 해줘야 합니다.
 */
