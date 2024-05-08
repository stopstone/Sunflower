package com.stopstone.sunflower.view.garden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.sunflower.OnDataChangedListener
import com.stopstone.sunflower.PlantClickListener
import com.stopstone.sunflower.data.Plant
import com.stopstone.sunflower.data.Storage
import com.stopstone.sunflower.databinding.ItemGardenBinding
import com.stopstone.sunflower.extension.setScaleImage

class GardenAdapter(
    private val items: MutableList<Plant>,
    private val listener: PlantClickListener,
    private val dataChangedListener: OnDataChangedListener?
) :
    RecyclerView.Adapter<GardenAdapter.GardenViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenViewHolder {
        return GardenViewHolder.from(parent, listener, dataChangedListener)
//        val inflatedView =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_garden, parent, false)
//        return GardenViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GardenViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class GardenViewHolder(
        private val binding: ItemGardenBinding,
        private val listener: PlantClickListener,
        private val onDataChangedListener: OnDataChangedListener?
    ) :

        RecyclerView.ViewHolder(binding.root) {
//        private val name: TextView = itemView.findViewById(R.id.tv_plant_item_name)
//        private val image: ImageView = itemView.findViewById(R.id.iv_plant_item_image)
//        private val btn: ImageButton = itemView.findViewById(R.id.btn_favorite_image)

        fun bind(item: Plant, listener: PlantClickListener) {
            setFavoritePlantDate(binding, item)
            itemView.setOnClickListener {
                listener.onPlantClick(item)
            }

            with(binding) {
                tvPlantItemName.text = item.name
                ivPlantItemImage.setScaleImage(item.image, itemView)
                btnFavoriteImage.isSelected = item.favorite
            }
        }

        private fun setFavoritePlantDate(binding: ItemGardenBinding, item: Plant) {
            with(binding.btnFavoriteImage) {
                setOnClickListener {
                    isSelected = !isSelected // 버튼 선택 반전
                    Storage.updateFavoriteStatus(item)

                    when (isSelected) {
                        true -> Storage.insertGardenPlantData(Storage.plantList.first { it.name == item.name })
                        false -> Storage.deleteGardenPlantData(Storage.plantList.first { it.name == item.name })
                    }
                    onDataChangedListener?.onDataChanged()
                }
            }
        }


        companion object {
            fun from(
                parent: ViewGroup,
                listener: PlantClickListener,
                dataChangedListener: OnDataChangedListener?
            ): GardenViewHolder {
                val binding =
                    ItemGardenBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return GardenViewHolder(binding, listener, dataChangedListener)
            }
        }
    }
}