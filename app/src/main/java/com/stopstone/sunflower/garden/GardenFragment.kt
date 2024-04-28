package com.stopstone.sunflower.garden

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.sunflower.PlantClickListener
import com.stopstone.sunflower.PlantDetailActivity
import com.stopstone.sunflower.R
import com.stopstone.sunflower.data.Plant
import com.stopstone.sunflower.data.Storage
import com.stopstone.sunflower.plant.PlantAdapter

class GardenFragment : Fragment(), PlantClickListener {
    private lateinit var gardenRecyclerView: RecyclerView
    private lateinit var adapter: GardenAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_garden, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gardenRecyclerView = requireActivity().findViewById(R.id.rv_garden_list)
    }

    override fun onStart() {
        super.onStart()
        val gardenList = Storage.getGardenList()
        Log.d("", gardenList.toString())
        adapter = GardenAdapter(gardenList, this)
        gardenRecyclerView.adapter = adapter
    }

    override fun onPlantClick(plant: Plant) {
        val data = Storage.plantList.first { it.name == plant.name }
        val intent = Intent(context, PlantDetailActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }
}