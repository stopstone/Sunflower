package com.stopstone.sunflower.view.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.databinding.ActivityPlantDetailBinding
import com.stopstone.sunflower.extension.setImage

class MovieDetailActivity : AppCompatActivity() {
    private val binding: ActivityPlantDetailBinding by lazy { ActivityPlantDetailBinding.inflate(layoutInflater) }
    private val viewModel: MovieDetailViewModel by viewModels<MovieDetailViewModel>()
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(TAG, "$TAG onCreate")

        movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data", Movie::class.java)!!
        } else {
            intent.getParcelableExtra("data")!!
        }

        viewModel.setMovie(movie)
        setLayout()

        binding.btnFavoriteImage.setOnClickListener {
            viewModel.toggleFavorite()
        }

        viewModel.movie.observe(this) { updatedMovie ->
            movie = updatedMovie
            binding.btnFavoriteImage.isSelected = movie.favorite
        }
    }

    private fun setLayout() {
        with(binding) {
            tvPlantDetailName.text = movie.title
            ivPlantDetailImage.setImage("$BASE_IMAGE${movie.posterPath}")
            tvMovieRatingLabel.text = "release: ${movie.releaseDate}"
            tvPlantDetailDescription.text = movie.overview
            btnFavoriteImage.isSelected = movie.favorite == true
        }
    }

    companion object {
        const val TAG = "PlantDetailActivity"
        const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}