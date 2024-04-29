package com.stopstone.sunflower.extention

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView

fun ImageView.load(imageUrl: Int?) {
    Thread {
        // 백그라운드 스레드에서 비트맵 처리 작업 실행
        val bitmap = imageUrl?.let { BitmapFactory.decodeResource(resources, it) }
        val scaledBitmap = bitmap?.let { Bitmap.createScaledBitmap(it, WIDTH, HEIGHT, false) }

        // UI 스레드에서 이미지뷰에 비트맵 설정
        post {
            scaledBitmap?.let { this.setImageBitmap(it) }
        }
    }.start()

}

const val WIDTH = 150
const val HEIGHT = 120