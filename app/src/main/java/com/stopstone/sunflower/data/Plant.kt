package com.stopstone.sunflower.data

import java.io.Serializable

data class Plant(
    val name: String,
    val image: Int?,
    val description: String,
    val favorite: Boolean
) : Serializable