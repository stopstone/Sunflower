package com.stopstone.sunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.stopstone.sunflower.data.Storage

class PlantFragment: Fragment() {
    private lateinit var plantList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plantList = requireActivity().findViewById(R.id.rv_plant_list)
        plantList.adapter = PlantAdapter(Storage.plantList)
    }
}