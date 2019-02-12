package com.meera.themovie.data.model

/**
 * MovieApiResponse to  parse the movie list response from teh server
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
data class MovieApiResponse(
    val page: Long,
    val results: List<Movie>,
    val total_results: Long,
    val total_pages: Long
) {

    fun isLastPage(): Boolean {
        return page >= total_pages
    }

}