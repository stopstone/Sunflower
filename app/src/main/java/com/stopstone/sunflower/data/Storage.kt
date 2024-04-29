package com.stopstone.sunflower.data

import com.stopstone.sunflower.R

object Storage {
    var plantList = getPlantData()
        private set

    private val gardenList = mutableListOf<Plant>()

    private fun getPlantData(): List<Plant> {
        return mutableListOf(
            Plant("Apple", R.drawable.plant_apple, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Avocado", R.drawable.plant_avocado, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Beet", R.drawable.plant_beet, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Bougainvillea", R.drawable.plant_bougainvillea, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Cilantro", R.drawable.plant_cilantro, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Eggplant", R.drawable.plant_eggplant, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Grape", R.drawable.plant_grape, "Aug 24 2024", "Aug 24 2024", false),
            Plant("Hibiscus", R.drawable.plant_hibiscus, "Aug 24 2024", "Aug 24 2024", false),
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