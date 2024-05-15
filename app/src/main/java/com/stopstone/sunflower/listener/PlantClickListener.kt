package com.stopstone.sunflower.listener

import com.stopstone.sunflower.data.Movie

interface PlantClickListener {
    fun onPlantClick(plant: Movie)
}