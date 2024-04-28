package com.stopstone.sunflower.data

import android.util.Log

object Storage {
    var plantList = getPlantData()
        private set

    private val gardenList = mutableListOf<Plant>()

    private fun getPlantData(): List<Plant> {
        return mutableListOf(
            Plant("Apple", null, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Avocado", null, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Beet", null, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Bougainvillea", null, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Cilantro", null, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Eggplant", null, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Grape", null, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Hibiscus", null, "Aug 24 2024", "Aug 24 2024", false),
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