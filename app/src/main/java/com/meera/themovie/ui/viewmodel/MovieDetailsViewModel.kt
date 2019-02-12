package com.meera.themovie.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.meera.themovie.data.model.Movie
import com.meera.themovie.data.remote.response.Response
import com.meera.themovie.data.remote.response.ResponseListener
import com.meera.themovie.data.repository.MovieRepository
import com.meera.themovie.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * MovieDetailsViewModel to handle the movie details ui and data
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/11/2019
 * Project : TheMovie
 */
class MovieDetailsViewModel @Inject constructor(private val movieRepository: MovieRepository) : BaseViewModel() {


    private val movieDetailsLiveData = MutableLiveData<Movie>()

    val title = ObservableField<String>()
    val overView = ObservableField<String>()


    fun getMovieDetailsLiveData() = movieDetailsLiveData

    fun loadMovieDetails(movieID: String) {
        movieRepository.getMovieDetails(movieID,
            object : ResponseListener<Movie> {

                override fun onResponse(result: Response<Movie>) {
                    movieDetailsLiveData.value = result.data
                    title.set(result.data?.title + " (" + result.data?.getReleaseYear() + ")")
                    overView.set(result.data?.overview)

                }
            })
    }
}