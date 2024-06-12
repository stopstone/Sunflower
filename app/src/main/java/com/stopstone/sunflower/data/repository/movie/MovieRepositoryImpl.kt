package com.stopstone.sunflower.data.repository.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stopstone.sunflower.data.source.LocalDataSource
import com.stopstone.sunflower.data.source.RemoteDataSource
import com.stopstone.sunflower.data.model.Movie
import com.stopstone.sunflower.storage.Storage
import javax.inject.Inject
import kotlin.random.Random

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val storage: Storage
) :
    MovieRepository {
    private var callback: ((List<Movie>) -> Unit)? = null
    override val isLocalSource = Random.nextBoolean()

    override fun loadMovieList(callback: (List<Movie>) -> Unit) {
        this.callback = callback
        when(Random.nextBoolean()) {
            true -> localDataSource
            false -> remoteDataSource
        }.apply { this.getMovieList(callback) }
    }
    override fun updateFavoriteStatus(movie: Movie) {
        val updateList = storage.movieList.map {
            if (it.id == movie.id) {
                it.copy(favorite = !it.favorite)
            } else {
                it
            }
        }
        storage.movieList.clear()
        storage.movieList.addAll(updateList)
        callback?.invoke(storage.movieList.toList())
    }

    override fun getMovieById(movieId: Int): LiveData<Movie> {
        val movie = storage.movieList.find { it.id == movieId }
        return MutableLiveData(movie)
    }

    override fun updateMovie(movie: Movie) {
        val index = storage.movieList.indexOfFirst { it.id == movie.id }
        if (index != -1) {
            storage.movieList[index] = movie
            callback?.invoke(storage.movieList.toList())
        }
    }
}