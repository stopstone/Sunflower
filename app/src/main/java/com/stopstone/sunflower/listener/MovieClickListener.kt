package com.stopstone.sunflower.listener

import com.stopstone.sunflower.data.Movie

interface MovieClickListener {
    fun onMovieClick(movie: Movie)
}