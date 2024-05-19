package com.stopstone.sunflower.data

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TMDBConnection private constructor() {

    init {
        Log.d("TMDBConnection", "TMDBConnection instance created.")
        // 앱을 실행할 때 단 한 번 로그가 출력됩니다.
        // -> 단 한 번 생성된다는 뜻입니다.
        // 화면 전환 후 복귀해도 다시 생성되지 않습니다.
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

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
