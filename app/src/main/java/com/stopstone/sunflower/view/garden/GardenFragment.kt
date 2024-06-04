package com.stopstone.sunflower.view.garden

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stopstone.sunflower.listener.OnDataChangedListener
import com.stopstone.sunflower.listener.MovieClickListener
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.storage.Storage
import com.stopstone.sunflower.databinding.FragmentGardenBinding
import com.stopstone.sunflower.presenter.MovieContract
import com.stopstone.sunflower.presenter.MoviePresenter
import com.stopstone.sunflower.view.MovieDetailActivity
import com.stopstone.sunflower.view.movie.MovieAdapter


// 좋아요 누른 식물 목록, Fragment()를 상속받는다. PlantClickListener 인터페이스를 상속받는다.
class GardenFragment : Fragment(), MovieClickListener, OnDataChangedListener,
    MovieContract.MovieView {
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this, this)
    }
    private val presenter: MoviePresenter by lazy {
        MoviePresenter(this, Storage)
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
    }

    override fun onResume() {
        super.onResume()
        showMovieList(Storage.movieList)
    }

    override fun onMovieClick(movie: Movie) {
        presenter.loadMovieDetailPage(movie, context)
    }

    override fun onDataChanged() {
        adapter.updateData(Storage.getGardenList())
    }

    override fun showMovieList(movie: List<Movie>) {
        onDataChanged()
    }

    override fun showMovieDetailPage(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("data", movie)
        startActivity(intent)
    }
}