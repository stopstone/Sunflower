// GardenFragment.kt
package com.stopstone.sunflower.view.garden

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.stopstone.sunflower.view.listener.MovieClickListener
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.data.repository.garden.GardenRepositoryImpl
import com.stopstone.sunflower.databinding.FragmentGardenBinding
import com.stopstone.sunflower.storage.Storage
import com.stopstone.sunflower.view.detail.MovieDetailActivity
import com.stopstone.sunflower.view.movie.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GardenFragment : Fragment(), MovieClickListener {
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this)
    }
    private val viewModel: GardenViewModel by viewModels {
        viewModelFactory {
            initializer { GardenViewModel(GardenRepositoryImpl(Storage)) }
        }
    }
    private var _binding: FragmentGardenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGardenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvGardenList.adapter = adapter

        viewModel.movieList.observe(viewLifecycleOwner) { movies ->
            val gardenList = movies
                .filter { it.favorite }
                .map { it.copy(viewType = 1) }
            adapter.updateData(gardenList)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchMovies()
    }

    override fun onMovieClick(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("data", movie)
        startActivity(intent)
    }

    override fun onFavoriteClick(movie: Movie) {
        viewModel.updateFavoriteStatus(movie)
    }
}