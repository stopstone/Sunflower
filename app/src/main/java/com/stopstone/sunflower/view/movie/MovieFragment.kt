package com.stopstone.sunflower.view.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.databinding.FragmentMovieBinding
import com.stopstone.sunflower.listener.MovieClickListener
import com.stopstone.sunflower.presenter.MovieContract
import com.stopstone.sunflower.presenter.MoviePresenter
import com.stopstone.sunflower.storage.Storage
import com.stopstone.sunflower.view.MovieDetailActivity

class MovieFragment : Fragment(), MovieClickListener, MovieContract.MovieView {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val adapter: MovieAdapter by lazy { MovieAdapter(this, null) }
    private val presenter: MoviePresenter by lazy { MoviePresenter(this, Storage) }

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
        presenter.loadMovieList()
    }

    override fun onResume() {
        super.onResume()
        showMovieList(Storage.movieList)

        binding.rvPlantList.setOnScrollChangeListener {  v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (binding.rvPlantList.canScrollVertically(1)) {
                presenter.loadMovieList()
            }
        }
    }

    override fun onMovieClick(movie: Movie) {
        presenter.loadMovieDetailPage(movie, context)
    }

    override fun showMovieList(movie: List<Movie>) {
        adapter.updateData(movie)
    }

    override fun showMovieDetailPage(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("data", movie)
        startActivity(intent)
    }
}