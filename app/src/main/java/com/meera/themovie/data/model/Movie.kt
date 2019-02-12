package com.meera.themovie.data.model

import com.meera.themovie.configuration.MovieConfiguration

/**
 * Movie Model to hold data for movie response
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
data class Movie(
    val id: Int,
    val vote_average: Double,
    val title: String,
    val overview: String,
    val adult: Boolean,
    val vote_count: Int,
    val video: Boolean,
    val popularity: Double,
    var poster_path: String,
    val original_language: String,
    val original_title: String,
    val backdrop_path: String,
    val release_date: String,
    val genres: List<Category>


) {

    fun getFormattedPosterPath(): String? {
        if (poster_path != null && !poster_path.startsWith("http")) {
            poster_path = String.format(MovieConfiguration.IMAGE_URL, poster_path)
        }
        return poster_path
    }

    fun getReleaseYear(): String? {

        if (release_date != null) {
            return release_date.substring(0, release_date.indexOf("-"))
        }
        return ""

    }

}