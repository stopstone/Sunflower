package com.stopstone.sunflower.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.databinding.ItemGardenBinding
import com.stopstone.sunflower.databinding.ItemMovieBinding
import com.stopstone.sunflower.extension.setScaleImage
import com.stopstone.sunflower.listener.MovieClickListener

class MovieAdapter(
    private val listener: MovieClickListener,
) : RecyclerView.Adapter<ViewHolder>() {
    private val items = mutableListOf<Movie>()
    var onClick: (movie: Movie) -> Unit = {}

    override fun getItemViewType(position: Int): Int {
        return when (items[position].viewType) {
            0 -> 0
            1 -> 1
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> MovieViewHolder(parent) {onClick(items[it])}
            1 -> GardenViewHolder(parent) {onClick(items[it])}
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
                is MovieViewHolder -> bind(item, listener)
                is GardenViewHolder -> bind(item, listener)
            }
        }
    }

    fun updateData(updatedItems: List<Movie>) {
        items.clear()
        items.addAll(updatedItems)
        notifyDataSetChanged()
    }

    class MovieViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemMovieBinding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        val onClick: (index: Int) -> Unit,
    ) :
        ViewHolder(binding.root) {
        init {
            binding.btnFavoriteImage.setOnClickListener {
                onClick(adapterPosition)
            }
        }

        fun bind(item: Movie, listener: MovieClickListener) {
            itemView.setOnClickListener {
                listener.onMovieClick(item)
            }

            with(binding) {
                tvPlantItemName.text = item.title
                ivPlantItemImage.setScaleImage("${GardenViewHolder.BASE_IMAGE}${item.posterPath}")
                btnFavoriteImage.isSelected = item.favorite
            }
        }
    }

    class GardenViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemGardenBinding = ItemGardenBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        ),
        val onClick: (index: Int) -> Unit,
    ) :

        ViewHolder(binding.root) {
            init {
                binding.btnFavoriteImage.setOnClickListener {
                    onClick(adapterPosition)
                }
            }

        fun bind(
            item: Movie,
            listener: MovieClickListener,
        ) {
            itemView.setOnClickListener {
                listener.onMovieClick(item)
            }

            with(binding) {
                tvPlantItemName.text = item.title
                ivPlantItemImage.setScaleImage("$BASE_IMAGE${item.posterPath}")
                val rating = "%.1f".format(item.voteAverage)
                tvPlantItemMovieRating.text = "Rating: $rating"
                btnFavoriteImage.isSelected = item.favorite
            }
        }

        companion object {
            const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
        }
    }
}