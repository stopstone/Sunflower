package com.stopstone.sunflower.data

data class Plant(
    val name: String,
    val imageUrl: String?,
    val planted: String,
    val lastWatered: String,
    val favorite: Boolean
)