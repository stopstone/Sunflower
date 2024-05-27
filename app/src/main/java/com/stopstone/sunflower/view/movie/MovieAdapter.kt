package com.stopstone.sunflower.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.storage.Storage
import com.stopstone.sunflower.databinding.ItemGardenBinding
import com.stopstone.sunflower.databinding.ItemMovieBinding
import com.stopstone.sunflower.extension.setScaleImage
import com.stopstone.sunflower.listener.OnDataChangedListener
import com.stopstone.sunflower.listener.MovieClickListener

class MovieAdapter(
    private val listener: MovieClickListener,
    private val onDataChangedListener: OnDataChangedListener?
) : RecyclerView.Adapter<ViewHolder>() {
    private val items = mutableListOf<Movie>()

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

    fun updateData(updatedItems: List<Movie>) {
        items.clear()
        items.addAll(updatedItems)
        notifyDataSetChanged()
    }

    class PlantViewHolder(private val binding: ItemMovieBinding) :
        ViewHolder(binding.root) {

        fun bind(item: Movie, listener: MovieClickListener) {
            setFavoritePlantDate(item)
            itemView.setOnClickListener {
                listener.onMovieClick(item)
            }

            with(binding) {
                tvPlantItemName.text = item.title
                ivPlantItemImage.setScaleImage("${GardenViewHolder.BASE_IMAGE}${item.poster_path}")
                btnFavoriteImage.isSelected = item.favorite
            }
        }

        private fun setFavoritePlantDate(movie: Movie) {

            with(binding.btnFavoriteImage) {
                setOnClickListener {
                    isSelected = !isSelected // 버튼 선택 반전
                    Storage.updateFavoriteStatus(movie)

                    if (isSelected) {
                        Storage.insertGardenPlantData(Storage.movieList.first { it.title == movie.title })
                    } else if (!isSelected) {
                        Storage.deleteGardenPlantData(Storage.movieList.first { it.title == movie.title })
                    }
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): PlantViewHolder {
                val binding = ItemMovieBinding.inflate(
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
            item: Movie,
            listener: MovieClickListener,
        ) {
            setFavoritePlantDate(binding, item)
            itemView.setOnClickListener {
                listener.onMovieClick(item)
            }

            with(binding) {
                tvPlantItemName.text = item.title
                ivPlantItemImage.setScaleImage("$BASE_IMAGE${item.poster_path}")
                val rating = "%.1f".format(item.vote_average)
                tvPlantItemMovieRating.text = "Rating: $rating"
                btnFavoriteImage.isSelected = item.favorite
            }
        }

        private fun setFavoritePlantDate(binding: ItemGardenBinding, movie: Movie) {
            with(binding.btnFavoriteImage) {
                setOnClickListener {
                    isSelected = !isSelected // 버튼 선택 반전
                    Storage.updateFavoriteStatus(movie)

                    when (isSelected) {
                        true -> Storage.insertGardenPlantData(Storage.movieList.first { it.title == movie.title })
                        false -> Storage.deleteGardenPlantData(Storage.movieList.first { it.title == movie.title })
                    }
                    onDataChangedListener?.onDataChanged()
                }
            }
        }

        companion object {
            const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
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