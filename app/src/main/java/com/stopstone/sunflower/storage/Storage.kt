package com.stopstone.sunflower.storage

import com.stopstone.sunflower.data.Movie

/*
    싱글톤패턴이란?
    해당 코드는 왜 싱글톤으로 구현했는가?
    싱글톤의 종류
 */

object Storage {
    private val gardenList = mutableListOf<Movie>() // 좋아요 식물 목록을 저장하는 리스트, mutableList로 선언

    fun insertGardenPlantData(movie: Movie) = gardenList.add(movie) // 좋아요를 누르면 호출되는 함수, gardenList 뒤에 추가한다.

    fun deleteGardenPlantData(movie: Movie) = gardenList.remove(movie.copy(favorite = true)) // 좋아요 취소를 누르면 호출되는 함수, plant의 favorite를 다시 변경하여 제거한다.

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
