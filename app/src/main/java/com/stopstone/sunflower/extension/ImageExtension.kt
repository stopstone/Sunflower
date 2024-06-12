package com.stopstone.sunflower.extension
// 확장함수를 그룹화하는 extension 패키지
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.stopstone.sunflower.R

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(getFullImageUrl(imageUrl))
        .centerCrop()
        .placeholder(R.color.black)
        .error(R.color.red)
        .into(this)
}

private fun getFullImageUrl(posterPath: String): String {
    return if (posterPath.startsWith("https://") || posterPath.startsWith("http://")) {
        posterPath
    } else {
        BASE_IMAGE+posterPath
    }
}
const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
