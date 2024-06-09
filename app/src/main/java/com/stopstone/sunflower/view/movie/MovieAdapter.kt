package com.stopstone.sunflower.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.databinding.ItemGardenBinding
import com.stopstone.sunflower.databinding.ItemMovieBinding
import com.stopstone.sunflower.extension.loadImage
import com.stopstone.sunflower.view.listener.MovieClickListener


class MovieAdapter(private val listener: MovieClickListener) : RecyclerView.Adapter<ViewHolder>() {
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
            0 -> MovieViewHolder(parent, items, listener) // items를 넘겨주는 방식 말고 다른 방법을 생각할 것
            1 -> GardenViewHolder(parent, items, listener)
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is MovieViewHolder -> holder.bind(item)
            is GardenViewHolder -> holder.bind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(updatedItems: List<Movie>) {
        items.clear()
        items.addAll(updatedItems)
        notifyDataSetChanged()
    }

    // 이렇게 되면 ClickListener는 한 번 불리지만, ViewHolder 생성 시 items를 계속 넘겨줘야 한다.
    class MovieViewHolder(
        parent: ViewGroup,
        items: List<Movie>,
        listener: MovieClickListener,
        private val binding: ItemMovieBinding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
    ) : ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onMovieClick(items[adapterPosition])
            }
            binding.btnFavoriteImage.setOnClickListener {
                listener.onFavoriteClick(items[adapterPosition])
            }
        }

        fun bind(item: Movie) {
            with(binding) {
                tvPlantItemName.text = item.title
                ivPlantItemImage.loadImage("${BASE_IMAGE}${item.posterPath}")
                btnFavoriteImage.isSelected = item.favorite
            }
        }
    }

    class GardenViewHolder(
        parent: ViewGroup,
        items: List<Movie>,
        listener: MovieClickListener,
        private val binding: ItemGardenBinding = ItemGardenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
    ) : ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onMovieClick(items[adapterPosition])
            }
            binding.btnFavoriteImage.setOnClickListener {
                listener.onFavoriteClick(items[adapterPosition])
            }
        }

        fun bind(item: Movie) {
            with(binding) {
                tvPlantItemName.text = item.title
                ivPlantItemImage.loadImage("$BASE_IMAGE${item.posterPath}")
                val rating = "%.1f".format(item.voteAverage)
                tvPlantItemMovieRating.text = "Rating: $rating"
                btnFavoriteImage.isSelected = item.favorite
            }
        }

    }

    companion object {
        const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}
