package com.stopstone.sunflower.extension
// 확장함수를 그룹화하는 extension 패키지
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.stopstone.sunflower.R
import com.stopstone.sunflower.databinding.ActivityPlantDetailBinding


// ImageView의 확장함수로 받은 이미지 resource를 Bitmap으로 변환하여 반환한다.
//fun ImageView.load(imageUrl: Int?): Bitmap? =
//    imageUrl?.let { BitmapFactory.decodeResource(resources, it) }!!
//

fun ImageView.setImage(imageUrl: Int, binding: ActivityPlantDetailBinding) {
    Glide.with(binding.root)
        .load(imageUrl)
        .placeholder(R.color.black)
        .error(R.color.red)
        .into(this)
}

// ImageView의 확장함수로 받은 이미지 resource를 Bitmap으로 변환하고, 사이즈를 조절하여 호출한 view에 그린다.
fun ImageView.setScaleImage(imageUrl: Int?, itemView: View) {
    Glide.with(itemView)
        .load(imageUrl)
        .override(WIDTH, HEIGHT)
        .placeholder(R.color.black)
        .error(R.color.red)
        .into(this)
}


// 이미지 사이즈
const val WIDTH = 150
const val HEIGHT = 120