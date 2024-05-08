package com.stopstone.sunflower

import android.widget.Adapter
import com.stopstone.sunflower.data.Plant

interface PlantClickListener {
    fun onPlantClick(plant: Plant)
}