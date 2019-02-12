package com.meera.themovie.data.remote.api

import com.meera.themovie.data.model.Movie
import com.meera.themovie.data.model.MovieApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * MovieApiService for maintaining all the APIs used in this app
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
interface MovieApiService {

    @GET("movie/popular?language=en-US&region=US")
    fun fetchPopularMoviesList(@Query("page") page: Long): Observable<MovieApiResponse>

    @GET("/3/movie/{movieId}")
    fun fetchMovieDetail(@Path("movieId") movieId: String): Observable<Movie>
}