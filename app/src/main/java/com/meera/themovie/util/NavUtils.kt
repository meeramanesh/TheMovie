package com.meera.themovie.util

import android.os.Bundle
import android.support.v4.app.Fragment
import com.meera.themovie.R
import com.meera.themovie.ui.fragment.MovieDetailsFragment
import com.meera.themovie.ui.fragment.MovieListFragment

/**
 * File Description
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovieApp1
 */
object NavUtils {

    fun pushMovieListFragment() {
        replaceFragment(MovieListFragment(), false)
    }

    fun pushMovieDetailsFragment(bundle: Bundle) {
        val fragment = MovieDetailsFragment()
        fragment.arguments = bundle
        replaceFragment(fragment, true)
    }


    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        FragmentUtils.replaceFragment(
            fragment,
            R.id.fragment_container,
            addToBackStack,
            FragmentAnimation.TRANSITION_POP
        )
    }

}