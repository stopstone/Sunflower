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


/*
* @Module은 생성자 주입이 불가능한 경우 사용합니다.
* 불가능한 경우는 다음과 같습니다.
* 1. 인터페이스를 주입해야하는 경우
* 인터페이스는 생성자가 존재하지 않기 때문에 생성자 주입이 불가능합니다.
*
* 2. 외부 라이브러리와 같은 소유하지 않고 있는 클래스의 경우 생성자 주입이 불가능합니다. (Retrofit, OkHttp, RoomDB)
* 외부 라이브러리의 경우 변경이 불가능하기 때문에 생성자 주입이 불가능합니다.
*
* 위와 같은 경우 Hilt의 모듈을 사용하여 해결할 수있습니다.
* Hilt 모듈은 @Module로 주석이 지정된 클래스입니다.
* 이 모듈은 특정 유형의 인스턴스를 제공하는 방법을 Hilt에 알려줍니다.
* Hilt모듈은 Gradle 모듈과는 다른 개념입니다.
* 그러나 Dagger 모듈과는 달리, Hilt 모듈에 @InstallIn 주석을 지정하여 각 모듈을 사용하거나 설치할 Android 클래스를 Hilt에 알려야 합니다.
*
* @InstallIn은 Hilt 모듈을 적절한 Android 클래스에 설치함으로써,
* 해당 모듈에서 제공하는 의존성의 수명 주기를 관리할 수 있습니다.
*
* @InstallIn 어노테이션은 다음과 같은 Android 클래스를 인자로 받을 수 있습니다:

SingletonComponent: 애플리케이션의 수명 주기에 바인딩되는 컴포넌트입니다. 이 컴포넌트에 설치된 모듈은 애플리케이션이 살아있는 동안 싱글톤 인스턴스를 제공합니다.
ActivityRetainedComponent: 액티비티의 수명 주기에 바인딩되지만 액티비티가 재생성될 때 유지되는 컴포넌트입니다. 이 컴포넌트에 설치된 모듈은 액티비티가 살아있는 동안 싱글톤 인스턴스를 제공합니다.
ActivityComponent: 액티비티의 수명 주기에 바인딩되는 컴포넌트입니다. 이 컴포넌트에 설치된 모듈은 액티비티 인스턴스마다 별도의 인스턴스를 제공합니다.
FragmentComponent: 프래그먼트의 수명 주기에 바인딩되는 컴포넌트입니다. 이 컴포넌트에 설치된 모듈은 프래그먼트 인스턴스마다 별도의 인스턴스를 제공합니다.
ViewComponent: 뷰의 수명 주기에 바인딩되는 컴포넌트입니다. 이 컴포넌트에 설치된 모듈은 뷰 인스턴스마다 별도의 인스턴스를 제공합니다.
ViewWithFragmentComponent: 뷰와 프래그먼트의 수명 주기에 바인딩되는 컴포넌트입니다. 이 컴포넌트에 설치된 모듈은 뷰와 프래그먼트 인스턴스마다 별도의 인스턴스를 제공합니다.
* */
@Module
// 애플리케이션의 생명주기에 바인딩 될 수 있도록 SingletonComponent 사용
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