package com.stopstone.sunflower.view.plant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.sunflower.PlantClickListener
import com.stopstone.sunflower.R
import com.stopstone.sunflower.data.Plant
import com.stopstone.sunflower.data.Storage
import com.stopstone.sunflower.databinding.ItemPlantBinding
import com.stopstone.sunflower.extension.setScaleImage

class PlantAdapter(private val items: List<Plant>, private val listener: PlantClickListener) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class PlantViewHolder(private val binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        private val name: TextView = itemView.findViewById(R.id.tv_plant_item_name)
//        private val image: ImageView = itemView.findViewById(R.id.iv_plant_item_image)
//        private val btn: ImageButton = itemView.findViewById(R.id.btn_favorite_image)

        fun bind(item: Plant, listener: PlantClickListener) {
            setFavoritePlantDate(item)
            itemView.setOnClickListener {
                listener.onPlantClick(item)
            }

            with(binding) {
                tvPlantItemName.text = item.name
                ivPlantItemImage.setScaleImage(item.image, itemView)
                btnFavoriteImage.isSelected = item.favorite
            }
//            name.text = item.name
//            image.setScaleImage(item.image, itemView)
//            btn.isSelected = item.favorite
        }

        private fun setFavoritePlantDate(item: Plant) {

            with(binding.btnFavoriteImage) {
                setOnClickListener {
                    isSelected = !isSelected // 버튼 선택 반전
                    Storage.updateFavoriteStatus(item)

                    if (isSelected) {
                        Storage.insertGardenPlantData(Storage.plantList.first { it.name == item.name })
                    } else if (!isSelected) {
                        Storage.deleteGardenPlantData(Storage.plantList.first { it.name == item.name })
                    }
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): PlantViewHolder {
                val binding = ItemPlantBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return PlantViewHolder(binding)
            }
        }
    }
}