package com.stopstone.sunflower

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.stopstone.sunflower.garden.GardenFragment
import com.stopstone.sunflower.plant.PlantFragment

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTabLayout()
    }

    private fun setTabLayout() {
        tabLayout = findViewById(R.id.tab_main)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val fragment = when (it.position) {
                        0 -> GardenFragment()
                        else -> PlantFragment()
                    }
                    supportFragmentManager.beginTransaction().replace(
                        R.id.container_main,
                        fragment
                    ).commit()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {  }

            override fun onTabReselected(tab: TabLayout.Tab?) {  }
        })
    }
}