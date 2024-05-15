package com.stopstone.sunflower.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.sunflower.listener.PlantClickListener
import com.stopstone.sunflower.MovieAdapter
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.databinding.FragmentMovieBinding
import com.stopstone.sunflower.storage.MovieStorage

class MovieFragment : Fragment(), PlantClickListener {
    //    private lateinit var plantList: RecyclerView
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this, null)
    }

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
//        plantList = view.findViewById(R.id.rv_plant_list)
        binding.rvPlantList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(MovieStorage.movieList)

        binding.rvPlantList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!binding.rvPlantList.canScrollVertically(1)) {   //최하단에 오면
                    MovieStorage.loadMovies { movieList ->
                        adapter.updateData(movieList)
                    }
                }
            }
        })

    }

    override fun onPlantClick(plant: Movie) {
        val data = MovieStorage.movieList.first { it.title == plant.title }
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }
}