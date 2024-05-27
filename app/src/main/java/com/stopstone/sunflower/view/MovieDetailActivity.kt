package com.stopstone.sunflower.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.stopstone.sunflower.data.Movie
import com.stopstone.sunflower.databinding.ActivityPlantDetailBinding
import com.stopstone.sunflower.extension.setImage
import com.stopstone.sunflower.storage.Storage

class MovieDetailActivity : AppCompatActivity() {
    private val binding : ActivityPlantDetailBinding by lazy { ActivityPlantDetailBinding.inflate(layoutInflater) }

    // PlantDetailActivity에서 보여질 View를 선언
    // 외부에 접근할 필요가 없으므로 private
    // lateinit로 선언하면 반드시 var로 선언해야한다.
//    private lateinit var plantName: TextView
//    private lateinit var plantImage: ImageView
//    private lateinit var favoriteButton: ImageButton
//    private lateinit var plantWateringNeeds: TextView
//    private lateinit var plantDescription: TextView
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // R.layout.activity_plant_detail 레이아웃과 인플레이트한다.
        Log.d(TAG, "$TAG onCreate")

        // 클릭한 식물 아이템을 Parcelable 데이터로 받아왔다.
        // getExtra는 파라미터로 name, class를 넘겨줘야 하기 때문에 멤버참조로 class로 넘겨준다.
        movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("data", Movie::class.java)!!
        } else {
            intent.getParcelableExtra("data")!!
        }

        setLayout(movie)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "$TAG onResume")

        with(binding.btnFavoriteImage) {
            setOnClickListener {
                Storage.updateFavoriteStatus(movie)
                isSelected = !isSelected

                when (isSelected) {
                    true -> Storage.insertGardenPlantData(Storage.movieList.first { item -> item.title == movie.title })
                    false -> Storage.deleteGardenPlantData(Storage.movieList.first { item -> item.title == movie.title })
                }
            }
        }
    }

    private fun setLayout(item: Movie) {
        with(binding) {
            tvPlantDetailName.text = item.title
            ivPlantDetailImage.setImage("$BASE_IMAGE${item.poster_path}")
            tvMovieRatingLabel.text = "release: ${item.release_date}"
            tvPlantDetailDescription.text = item.overview
            btnFavoriteImage.isSelected = item.favorite == true
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "$TAG onStart")
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

    companion object {
        const val TAG = "PlantDetailActivity"
        const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500"
    }
}

