package com.stopstone.sunflower.view.plant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.sunflower.PlantClickListener
import com.stopstone.sunflower.view.common.PlantDetailActivity
import com.stopstone.sunflower.R
import com.stopstone.sunflower.SunflowerAdapter
import com.stopstone.sunflower.data.Plant
import com.stopstone.sunflower.data.Storage
import com.stopstone.sunflower.databinding.FragmentPlantBinding

class PlantFragment: Fragment(), PlantClickListener {
    private lateinit var plantList: RecyclerView
    private var _binding: FragmentPlantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        plantList = view.findViewById(R.id.rv_plant_list)
        plantList = binding.rvPlantList
        plantList.adapter = SunflowerAdapter(Storage.plantList as MutableList<Plant>, this, null)

        Log.d("", plantList.toString())
    }


    override fun onPlantClick(plant: Plant) {
        val data = Storage.plantList.first { it.name == plant.name }
        val intent = Intent(context, PlantDetailActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }
}