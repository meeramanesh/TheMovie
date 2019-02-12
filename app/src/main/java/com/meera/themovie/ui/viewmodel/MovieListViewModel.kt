package com.meera.themovie.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.meera.themovie.data.model.MovieApiResponse
import com.meera.themovie.data.remote.response.Response
import com.meera.themovie.data.remote.response.ResponseListener
import com.meera.themovie.data.repository.MovieRepository
import com.meera.themovie.ui.base.BaseViewModel
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) : BaseViewModel() {

    private val moviesListLiveData = MutableLiveData<MovieApiResponse>()


    fun getMoviesLiveData() = moviesListLiveData


    fun loadMovieList(currentPage: Long) {
        movieRepository.getMovieList(currentPage,
            object : ResponseListener<MovieApiResponse> {

                override fun onResponse(result: Response<MovieApiResponse>) {
                    moviesListLiveData.value = result.data
                }
            })
    }

    fun isLastPage(): Boolean {
        if (moviesListLiveData.value != null) {
            return moviesListLiveData.value!!.isLastPage()
        }
        return true
    }
}
