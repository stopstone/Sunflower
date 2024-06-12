package com.stopstone.sunflower.data.source

import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.storage.Storage
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val storage: Storage) : DataSource {
    override fun getMovieList(callback: (List<Movie>) -> Unit) {
        initMovieListIfEmpty()
        callback(storage.movieList)
    }

    private fun initMovieListIfEmpty() {
        if (storage.movieList.isEmpty()) {
            storage.movieList.addAll(storage.localMovieList)
        }
    }
}