package com.stopstone.sunflower.data

import com.stopstone.sunflower.R

object Storage {
    var plantList = getPlantData()
        private set

    private val gardenList = mutableListOf<Plant>()

    private fun getPlantData(): List<Plant> {
        return mutableListOf(
            Plant("Apple", R.drawable.plant_apple, "The Apple Description", false),
            Plant("Avocado", R.drawable.plant_avocado, "The Avocado Description", false),
            Plant("Beet", R.drawable.plant_beet, "The Beet Description", false),
            Plant("Bougainvillea", R.drawable.plant_bougainvillea, "The Bougainvillea Description", false),
            Plant("Cilantro", R.drawable.plant_cilantro, "The Cilantro Description", false),
            Plant("Eggplant", R.drawable.plant_eggplant, "The Eggplant Description", false),
            Plant("Grape", R.drawable.plant_grape, "The Grape Description", false),
            Plant("Hibiscus", R.drawable.plant_hibiscus, "The Hibiscus Description", false),
        )
    }

    fun updateFavoriteStatus(plant: Plant) {
        plantList = plantList.map {
            if (it.name == plant.name) {
                it.copy(favorite = !it.favorite)
            } else {
                it
            }
        }
    }

    fun insertGardenPlantData(plant: Plant) = gardenList.add(plant)

    fun deleteGardenPlantData(plant: Plant) = gardenList.remove(plant.copy(favorite = true))

    fun getGardenList() = gardenList
}