package com.stopstone.sunflower.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView

fun ImageView.load(imageUrl: Int?): Bitmap? = imageUrl?.let { BitmapFactory.decodeResource(resources, it) }!!

fun ImageView.setImage(imageUrl: Int?) {
    Thread {
        post {
            load(imageUrl)?.let { this.setImageBitmap(it) }
        }
    }.start()
}

fun ImageView.setScaleImage(imageUrl: Int?) {
    Thread {
        val scaledBitmap = load(imageUrl)?.let { Bitmap.createScaledBitmap(it, WIDTH, HEIGHT, false) }
        post {
            scaledBitmap?.let { this.setImageBitmap(it) }
        }
    }.start()
}

const val WIDTH = 150
const val HEIGHT = 120