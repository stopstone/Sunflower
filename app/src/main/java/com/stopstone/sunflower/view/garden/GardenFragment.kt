// GardenFragment.kt
package com.stopstone.sunflower.view.garden

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stopstone.sunflower.listener.MovieClickListener
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.databinding.FragmentGardenBinding
import com.stopstone.sunflower.view.detail.MovieDetailActivity
import com.stopstone.sunflower.view.movie.MovieAdapter

class GardenFragment : Fragment(), MovieClickListener {
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this)
    }
    private val viewModel: GardenViewModel by viewModels<GardenViewModel>()
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

        adapter.onClick = {
            viewModel.updateFavoriteStatus(it)
        }
    }

    override fun onMovieClick(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("data", movie)
        startActivity(intent)
    }
}