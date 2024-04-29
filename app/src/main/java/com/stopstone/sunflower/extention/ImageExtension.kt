package com.stopstone.sunflower.extention

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView

fun ImageView.load(imageUrl: Int?) {
    val bitmap = imageUrl?.let { BitmapFactory.decodeResource(resources, it) }
    val scaledBitmap = bitmap?.let { Bitmap.createScaledBitmap(it, WIDTH, HEIGHT, false) }

    scaledBitmap?.let { this.setImageBitmap(it) }
}

const val WIDTH = 150
const val HEIGHT = 120