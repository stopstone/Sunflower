package com.stopstone.sunflower.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.stopstone.sunflower.databinding.ActivityMainBinding
import com.stopstone.sunflower.view.movie.MovieFragment

class MainActivity : AppCompatActivity() {
    //    private lateinit var tabLayout: TabLayout
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 탭 전환후 복귀 시 리사이클러 뷰 초기화 현상 수정
    private val gardenFragment = GardenFragment()
    private val movieFragment = MovieFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setTabLayout()

        Log.d(TAG, "$TAG onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$TAG onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$TAG onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "$TAG onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "$TAG onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "$TAG onDestroy")
    }

    private fun setTabLayout() {
//        val tabLayout = findViewById(R.id.tab_main)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val fragment = when (it.position) {
                        0 -> gardenFragment
                        1 -> movieFragment
                        else -> throw IllegalArgumentException("Invalid tab position")
                    }
                    supportFragmentManager.beginTransaction().apply {
                        replace(binding.containerMain.id, fragment as Fragment)
                        commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }
}