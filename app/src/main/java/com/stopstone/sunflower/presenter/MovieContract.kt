package com.stopstone.sunflower.presenter

import com.stopstone.sunflower.data.Movie

interface MovieContract {
    interface MovieView {
        fun showMovieList(movie: List<Movie>)
    }

    interface MoviePresenter {
        fun loadMovieList()
    }
}