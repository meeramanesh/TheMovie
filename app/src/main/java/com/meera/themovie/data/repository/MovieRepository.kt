package com.meera.themovie.data.repository

import com.meera.themovie.data.model.Movie
import com.meera.themovie.data.model.MovieApiResponse
import com.meera.themovie.data.remote.api.MovieApiService
import com.meera.themovie.data.remote.response.ResponseListener
import com.meera.themovie.schedulers.BaseScheduler
import javax.inject.Singleton

/**
 * MovieRepository for handling  the  movie specific requests
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
@Singleton
class MovieRepository(private val movieApiService: MovieApiService, scheduler: BaseScheduler) :
    BaseRepository(scheduler) {


    /**
     * The method is to call Popular Movie List
     * @param responseListener: Response Listener Callback
     */
    fun getMovieList(page: Long, responseListener: ResponseListener<MovieApiResponse>) {
        performRequest(movieApiService.fetchPopularMoviesList(page), responseListener)
    }


    /**
     * The method is to get a Movie Details
     * @param responseListener: Response Listener Callback
     */
    fun getMovieDetails(movieID: String, responseListener: ResponseListener<Movie>) {
        performRequest(movieApiService.fetchMovieDetail(movieID), responseListener)
    }


}