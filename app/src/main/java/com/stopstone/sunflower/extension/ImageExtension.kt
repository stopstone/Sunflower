package com.stopstone.sunflower.extension
// 확장함수를 그룹화하는 extension 패키지
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.stopstone.sunflower.R


// ImageView의 확장함수로 받은 이미지 resource를 Bitmap으로 변환하여 반환한다.
//fun ImageView.load(imageUrl: Int?): Bitmap? =
//    imageUrl?.let { BitmapFactory.decodeResource(resources, it) }!!
//

fun ImageView.setImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.color.black)
        .error(R.color.red)
        .into(this)
}

// ImageView의 확장함수로 받은 이미지 resource를 Bitmap으로 변환하고, 사이즈를 조절하여 호출한 view에 그린다.
fun ImageView.setScaleImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.color.black)
        .error(R.color.red)
        .into(this)
}

