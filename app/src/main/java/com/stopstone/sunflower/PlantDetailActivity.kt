package com.stopstone.sunflower

import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.stopstone.sunflower.data.Plant
import com.stopstone.sunflower.data.Storage
import com.stopstone.sunflower.extention.load

class PlantDetailActivity: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        val plant = intent.getSerializableExtra("data", Plant::class.java)

        val plantName: TextView = findViewById(R.id.tv_plant_detail_name)
        val image : ImageView = findViewById(R.id.iv_plant_detail_image)
        val favoriteButton: ImageButton = findViewById(R.id.btn_favorite_image)

        plant?.let {
            plantName.text = plant.name
            image.load(plant.imageUrl)
            favoriteButton.isSelected = plant.favorite == true
        }

        favoriteButton.setOnClickListener {
            plant?.let {
                Storage.updateFavoriteStatus(it)
                favoriteButton.isSelected = !favoriteButton.isSelected // 버튼 선택 반전

                if (favoriteButton.isSelected) {
                    Storage.insertGardenPlantData(Storage.plantList.first { item ->  item.name == plant.name })
                } else if (!favoriteButton.isSelected) {
                    Storage.deleteGardenPlantData(Storage.plantList.first { item ->  item.name == plant.name })
                }
            }
        }
    }
}