package com.stopstone.sunflower.plant

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
import com.stopstone.sunflower.extension.setScaleImage

class PlantAdapter(private val items: List<Plant>, private val listener: PlantClickListener) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.tv_plant_item_name)
        private val image: ImageView = itemView.findViewById(R.id.iv_plant_item_image)
        private val btn: ImageButton = itemView.findViewById(R.id.btn_favorite_image)

        fun bind(item: Plant, listener: PlantClickListener) {
            setFavoritePlantDate(item)
            itemView.setOnClickListener {
                listener.onPlantClick(item)
            }

            name.text = item.name
            image.setScaleImage(item.image)
            btn.isSelected = item.favorite
        }

        private fun setFavoritePlantDate(item: Plant) {
            btn.setOnClickListener {
                btn.isSelected = !btn.isSelected // 버튼 선택 반전
                Storage.updateFavoriteStatus(item)

                if (btn.isSelected) {
                    Storage.insertGardenPlantData(Storage.plantList.first { it.name == item.name })
                } else if (!btn.isSelected) {
                    Storage.deleteGardenPlantData(Storage.plantList.first { it.name == item.name })
                }
            }
        }
    }
}