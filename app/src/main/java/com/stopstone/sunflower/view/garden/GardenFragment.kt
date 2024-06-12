// GardenFragment.kt
package com.stopstone.sunflower.view.garden

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.databinding.FragmentGardenBinding
import com.stopstone.sunflower.view.detail.MovieDetailActivity
import com.stopstone.sunflower.view.listener.MovieClickListener
import com.stopstone.sunflower.view.movie.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
/*
* Android 클래스에 종속항목을 설정할 수 있습니다. (Activity, Fragment, Service, BroadcastReceiver, View 와 같은 클래스를 제공합니다.)
* 이 밖에도 Application(@HiltAndroidApp), ViewModel(HiltViewModel)을 지원합니다.
* 단, Hilt는 AppCompatActivity, ComponentActivity 를 확장하는 액티비티만 지원합니다.
* 프래그먼트의 경우 androidx.fragment를 확장해야한 지원 가능합니다.
* */
class GardenFragment : Fragment(), MovieClickListener {
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this)
    }

    private val viewModel: GardenViewModel by viewModels()
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
        viewModel.loadMovies()
        binding.rvGardenList.adapter = adapter

        viewModel.movieList.observe(viewLifecycleOwner) { movies ->
            val gardenList = movies
                .filter { it.favorite }
                .map { it.copy(viewType = 1) }
            adapter.updateData(gardenList)
        }
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