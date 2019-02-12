package com.meera.themovie.di.module

import com.meera.themovie.ui.fragment.FilterDialogFragment
import com.meera.themovie.ui.fragment.MovieDetailsFragment
import com.meera.themovie.ui.fragment.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module holding all Fragments
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment


    @ContributesAndroidInjector
    abstract fun contributeFilterDialogFragment(): FilterDialogFragment

}