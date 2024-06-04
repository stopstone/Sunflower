package com.stopstone.sunflower.view.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.databinding.FragmentMovieBinding
import com.stopstone.sunflower.listener.MovieClickListener
import com.stopstone.sunflower.view.detail.MovieDetailActivity

class MovieFragment : Fragment(), MovieClickListener {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val adapter: MovieAdapter by lazy { MovieAdapter(this) }
    private val viewModel: MovieViewModel by viewModels<MovieViewModel>()
//    private val presenter: MoviePresenter by lazy { MoviePresenter(this, Storage) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPlantList.adapter = adapter
//        presenter.loadMovieList()
        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }

        adapter.onClick = { movie ->
            viewModel.updateFavoriteStatus(movie)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadMovieList()
        binding.rvPlantList.setOnScrollChangeListener {  v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (binding.rvPlantList.canScrollVertically(1)) {
                viewModel.loadMovieList()
            }
        }
    }

    override fun onMovieClick(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("data", movie)
        startActivity(intent)
    }
}