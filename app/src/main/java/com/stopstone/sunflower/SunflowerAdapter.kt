package com.stopstone.sunflower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.stopstone.sunflower.data.Plant
import com.stopstone.sunflower.data.Storage
import com.stopstone.sunflower.databinding.ItemGardenBinding
import com.stopstone.sunflower.databinding.ItemPlantBinding
import com.stopstone.sunflower.extension.setScaleImage

class SunflowerAdapter(
    private val items: MutableList<Plant>,
    private val listener: PlantClickListener,
    private val onDataChangedListener: OnDataChangedListener?
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (items[position].viewType) {
            0 -> 0
            1 -> 1
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> PlantViewHolder.from(parent)
            1 -> GardenViewHolder.from(parent, onDataChangedListener)
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder) {
            when (this) {
                is PlantViewHolder -> bind(item, listener)
                is GardenViewHolder -> bind(item, listener)
            }
        }
    }

    fun updateData(updatedItems: List<Plant>) {
        items.clear()
        items.addAll(updatedItems)
        notifyDataSetChanged()
    }


    class PlantViewHolder(private val binding: ItemPlantBinding) :
        ViewHolder(binding.root) {

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

    class GardenViewHolder(
        private val binding: ItemGardenBinding,
        private val onDataChangedListener: OnDataChangedListener?
    ) :

        ViewHolder(binding.root) {

        fun bind(
            item: Plant,
            listener: PlantClickListener,
        ) {
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
                dataChangedListener: OnDataChangedListener?
            ): GardenViewHolder {
                val binding =
                    ItemGardenBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return GardenViewHolder(binding, dataChangedListener)
            }
        }
    }
}