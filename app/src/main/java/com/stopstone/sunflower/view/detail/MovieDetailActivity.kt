package com.stopstone.sunflower.view.detail

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.databinding.ActivityPlantDetailBinding
import com.stopstone.sunflower.extension.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private val binding: ActivityPlantDetailBinding by lazy { ActivityPlantDetailBinding.inflate(layoutInflater) }
    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root).apply {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            ) //
        }

        movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data", Movie::class.java)!!
        } else {
            intent.getParcelableExtra("data")!!
        }

        viewModel.setMovie(movie.id)
        setLayout()

        viewModel.movie.observe(this) { updatedMovie ->
            movie = updatedMovie
            binding.btnFavoriteImage.isSelected = movie.favorite
        }

        binding.btnFavoriteImage.setOnClickListener {
            viewModel.toggleFavorite()
        }

        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setLayout() {
        with(binding) {
            tvPlantDetailName.text = movie.title
            ivPlantDetailImage.loadImage(movie.posterPath)
            tvMovieRatingLabel.text = "release: ${movie.releaseDate}"
            tvPlantDetailDescription.text = movie.overview
            btnFavoriteImage.isSelected = movie.favorite == true
        }
    }

    companion object {
        const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}