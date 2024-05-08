package com.stopstone.sunflower.view.garden

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.sunflower.OnDataChangedListener
import com.stopstone.sunflower.PlantClickListener
import com.stopstone.sunflower.view.common.PlantDetailActivity
import com.stopstone.sunflower.R
import com.stopstone.sunflower.SunflowerAdapter
import com.stopstone.sunflower.data.Plant
import com.stopstone.sunflower.data.Storage
import com.stopstone.sunflower.databinding.FragmentGardenBinding


// 좋아요 누른 식물 목록, Fragment()를 상속받는다. PlantClickListener 인터페이스를 상속받는다.
class GardenFragment : Fragment(), PlantClickListener, OnDataChangedListener {
//    private lateinit var gardenRecyclerView: RecyclerView
    private lateinit var adapter: SunflowerAdapter

    private var _binding : FragmentGardenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGardenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        gardenRecyclerView = view.findViewById(R.id.rv_garden_list)

        adapter = SunflowerAdapter(Storage.getGardenList() as MutableList<Plant>, this, this)
        binding.rvGardenList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onPlantClick(plant: Plant) {
        val data = Storage.plantList.first { it.name == plant.name } // 식물 리스트 중 클릭된 아이템과 같은 아이템을 찾는다.
        // list이기 때문에 first를 사용하여 해당 첫번째 요소로 데이터를 찾는다.
        val intent = Intent(context, PlantDetailActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    override fun onDataChanged() {
        adapter.updateData(Storage.getGardenList())
    }
}