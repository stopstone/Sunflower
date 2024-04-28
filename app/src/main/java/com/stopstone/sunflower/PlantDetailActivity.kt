package com.stopstone.sunflower

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.stopstone.sunflower.data.Plant
import com.stopstone.sunflower.data.Storage

class PlantDetailActivity: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        val plant = intent.getSerializableExtra("data", Plant::class.java)
        val favoriteButton: ImageButton = findViewById(R.id.btn_favorite_image)

        favoriteButton.isSelected = plant?.favorite == true
        favoriteButton.setOnClickListener {
            plant?.let {
                Storage.updateFavoriteStatus(it)
                favoriteButton.isSelected = !favoriteButton.isSelected // 버튼 선택 반전
            }
        }
    }
}