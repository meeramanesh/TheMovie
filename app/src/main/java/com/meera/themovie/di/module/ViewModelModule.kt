package com.meera.themovie.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.meera.themovie.di.keys.ViewModelKey
import com.meera.themovie.factory.ViewModelFactory
import com.meera.themovie.ui.viewmodel.MainActivityViewModel
import com.meera.themovie.ui.viewmodel.MovieDetailsViewModel
import com.meera.themovie.ui.viewmodel.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module holding all ViewModels
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie

 */
@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    fun bindMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

}