package com.stopstone.sunflower

import android.app.Application
import android.util.Log
import com.stopstone.sunflower.storage.MovieStorage

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MovieStorage.loadMovies(null)
        Log.d("", "Application Create")
    }
}

// 앱이 실행될때 호출된다.