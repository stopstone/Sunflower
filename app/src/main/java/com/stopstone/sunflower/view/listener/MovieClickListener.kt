package com.stopstone.sunflower.view.listener

import com.stopstone.sunflower.data.model.Movie

interface MovieClickListener {
    fun onMovieClick(movie: Movie)
}