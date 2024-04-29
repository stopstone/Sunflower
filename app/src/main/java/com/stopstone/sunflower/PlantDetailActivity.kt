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
import com.stopstone.sunflower.extension.setImage

class PlantDetailActivity: AppCompatActivity() {
    private lateinit var plantName: TextView
    private lateinit var plantImage: ImageView
    private lateinit var favoriteButton: ImageButton
    private lateinit var plantWateringNeeds: TextView
    private lateinit var plantDescription: TextView
    private lateinit var plant: Plant

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_detail)

        plant = intent.getSerializableExtra("data", Plant::class.java)!!
        plantName = findViewById(R.id.tv_plant_detail_name)
        plantWateringNeeds = findViewById(R.id.tv_plant_watering_needs)
        plantDescription = findViewById(R.id.tv_plant_detail_description)
        plantImage = findViewById(R.id.iv_plant_detail_image)
        favoriteButton = findViewById(R.id.btn_favorite_image)

        favoriteButton.setOnClickListener {
                Storage.updateFavoriteStatus(plant)
                favoriteButton.isSelected = !favoriteButton.isSelected // 버튼 선택 반전

                if (favoriteButton.isSelected) {
                    Storage.insertGardenPlantData(Storage.plantList.first { item ->  item.name == plant.name })
                } else if (!favoriteButton.isSelected) {
                    Storage.deleteGardenPlantData(Storage.plantList.first { item ->  item.name == plant.name })
                }
        }
    }

    override fun onResume() {
        super.onResume()
        setLayout(plant)
    }

    private fun setLayout(plant: Plant?) {
        plant?.let {
            plantName.text = plant.name
            plantImage.setImage(plant.image)
            plantWateringNeeds.text = "every 0 days"
            plantDescription.text = plant.description
            favoriteButton.isSelected = plant.favorite == true
        }
    }
}