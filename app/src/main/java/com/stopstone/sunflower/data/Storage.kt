package com.stopstone.sunflower.data

object Storage {
    var plantList = getPlantData()
        private set

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
}