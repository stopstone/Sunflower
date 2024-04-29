package com.stopstone.sunflower.data

import java.io.Serializable

data class Plant(
    val name: String,
    val imageUrl: Int?,
    val planted: String,
    val lastWatered: String,
    val favorite: Boolean
) : Serializable