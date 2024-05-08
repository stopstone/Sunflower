package com.stopstone.sunflower.data

import com.stopstone.sunflower.R

/*
    싱글톤패턴이란?
    해당 코드는 왜 싱글톤으로 구현했는가?
    싱글톤의 종류
 */

object Storage {
    var plantList = getPlantData() // 식물 전체 정보 리스트를 담고있는 List
        // var인 이유는 favorite의 상태를 관리하기 위해 데이터 변경이 필요하다.
        // private set으로 외부로부터 수정을 막는다.
        private set

    private val gardenList = mutableListOf<Plant>() // 좋아요 식물 목록을 저장하는 리스트, mutableList로 선언

    private fun getPlantData(): List<Plant> {
        return listOf(
            Plant("Apple", R.drawable.plant_apple, "The Apple Description", false, 0),
            Plant("Avocado", R.drawable.plant_avocado, "The Avocado Description", false, 0),
            Plant("Beet", R.drawable.plant_beet, "The Beet Description", false, 0),
            Plant("Bougainvillea", R.drawable.plant_bougainvillea, "The Bougainvillea Description", false, 0),
            Plant("Cilantro", R.drawable.plant_cilantro, "The Cilantro Description", false, 0),
            Plant("Eggplant", R.drawable.plant_eggplant, "The Eggplant Description", false, 0),
            Plant("Grape", R.drawable.plant_grape, "The Grape Description", false, 0),
            Plant("Hibiscus", R.drawable.plant_hibiscus, "The Hibiscus Description", false, 0),
        )
    } // Plant정보를 담고있는 리스트를 반환하는 함수

    fun updateFavoriteStatus(plant: Plant) {
        plantList = plantList.map {
            if (it.name == plant.name) {
                it.copy(favorite = !it.favorite)
            } else {
                it
            }
        }
    }
    /*
    * 좋아요 버튼이 눌리면 호출되는 함수
    * 좋아요 눌린 아이템과 같은 식물의 좋아요 상태를 변경하여 copy를 통해 저장한다.
    * */

    fun insertGardenPlantData(plant: Plant) = gardenList.add(plant) // 좋아요를 누르면 호출되는 함수, gardenList 뒤에 추가한다.

    fun deleteGardenPlantData(plant: Plant) = gardenList.remove(plant.copy(favorite = true)) // 좋아요 취소를 누르면 호출되는 함수, plant의 favorite를 다시 변경하여 제거한다.

    fun getGardenList() = gardenList.map { it.copy(viewType = 1)} // 좋아요 목록을 불러오는 함수
}


/**
 * 개선사항
 * 현재 코드는 좋아요가 눌린 순서대로 리스트를 보여주고 있다.
 * plantList(식물 리스트)와 같은 순서로 정렬하여 보여주고 싶으면 어떻게 해야할까?
 *
 * -> Plant data class에 id를 추가하여 gardenList의 호출이 있을때 정렬을 하여 리스트에 보여준다.
 * gardenList의 변경이 있을때보다 호출빈도가 적을 수 있을 것 같아, 성능면에서 호출 시 정렬하는 것이 좋을 것 같다.
 */
