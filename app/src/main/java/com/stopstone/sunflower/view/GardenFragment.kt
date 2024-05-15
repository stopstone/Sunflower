package com.stopstone.sunflower.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stopstone.sunflower.listener.OnDataChangedListener
import com.stopstone.sunflower.listener.PlantClickListener
import com.stopstone.sunflower.MovieAdapter
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.storage.MovieStorage
import com.stopstone.sunflower.storage.Storage
import com.stopstone.sunflower.databinding.FragmentGardenBinding


// 좋아요 누른 식물 목록, Fragment()를 상속받는다. PlantClickListener 인터페이스를 상속받는다.
class GardenFragment : Fragment(), PlantClickListener, OnDataChangedListener {
    //    private lateinit var gardenRecyclerView: RecyclerView
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(this, this)
    }

    private var _binding: FragmentGardenBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "$TAG onAttach")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "$TAG onCreate")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGardenBinding.inflate(inflater, container, false)
        Log.d(TAG, "$TAG onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        gardenRecyclerView = view.findViewById(R.id.rv_garden_list)
        binding.rvGardenList.adapter = adapter
        Log.d(TAG, "$TAG onViewCreated")


    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "$TAG onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$TAG onStart")
    }

    override fun onResume() {
        super.onResume()
        adapter.updateData(Storage.getGardenList())
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "$TAG onDetach")
    }
    override fun onPlantClick(movie: Movie) {
        val data =
            MovieStorage.movieList.first { it.title == movie.title } // 식물 리스트 중 클릭된 아이템과 같은 아이템을 찾는다.
        // list이기 때문에 first를 사용하여 해당 첫번째 요소로 데이터를 찾는다.
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    override fun onDataChanged() {
        adapter.updateData(Storage.getGardenList())
    }

    companion object {
        private const val TAG = "GardenFragment"
    }
}