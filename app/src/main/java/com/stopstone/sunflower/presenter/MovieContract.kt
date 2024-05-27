package com.stopstone.sunflower.presenter

import android.content.Context
import com.stopstone.sunflower.data.Movie

interface MovieContract {
    interface MovieView {
        fun showMovieList(movie: List<Movie>)
        fun showMovieDetailPage(movie: Movie)
    }

    interface MoviePresenter {
        fun loadMovieList()
        fun loadMovieDetailPage(movie: Movie, context: Context?)
    }
}