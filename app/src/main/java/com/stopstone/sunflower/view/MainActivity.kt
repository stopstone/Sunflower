package com.stopstone.sunflower.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.stopstone.sunflower.R
import com.stopstone.sunflower.databinding.ActivityMainBinding
import com.stopstone.sunflower.view.garden.GardenFragment
import com.stopstone.sunflower.view.movie.MovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    // 탭 전환후 복귀 시 리사이클러 뷰 초기화 현상 수정
    private val gardenFragment = GardenFragment()
    private val movieFragment = MovieFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setInitialFragment() // 초기 화면 설정
        setTabLayout()
    }

    private fun setInitialFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.containerMain.id, movieFragment)
            commit()
        }
    }

    private fun setTabLayout() {
        // 탭을 전환하는 방식을 더 고민해 볼 수 있음
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val fragment = when (it.position) {
                        0 -> movieFragment
                        1 -> gardenFragment
                        else -> throw IllegalArgumentException(getString(R.string.invalid_tab_position))
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
}