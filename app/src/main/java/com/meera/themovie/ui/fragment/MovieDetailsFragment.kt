package com.meera.themovie.ui.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.android.databinding.library.baseAdapters.BR
import com.meera.themovie.R
import com.meera.themovie.configuration.Constants
import com.meera.themovie.data.model.Category
import com.meera.themovie.databinding.FragmentMovieDetailsBinding
import com.meera.themovie.ui.base.BaseFragment
import com.meera.themovie.ui.view.adapter.CategoryListAdapter
import com.meera.themovie.ui.viewmodel.MovieDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*


/**
 * File Description
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/11/2019
 * Project : TheMovie
 */
class MovieDetailsFragment : BaseFragment<MovieDetailsViewModel, FragmentMovieDetailsBinding>() {


    private lateinit var categoryListAdapter: CategoryListAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<MovieDetailsViewModel> {
        return MovieDetailsViewModel::class.java
    }

    override fun getTitle(): String {
        return getString(R.string.movie_details)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_movie_details


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieID = arguments?.getInt(Constants.MOVIE_ID)
        viewModel.loadMovieDetails(movieID.toString())


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeMovieDetailsResponse()

    }

    private fun observeMovieDetailsResponse() {
        viewModel.getMovieDetailsLiveData().observe(this, Observer {

            loadMoviePoster()
            displayCategories(it?.genres!!)
        })
    }

    private fun displayCategories(categories: List<Category>) {
        categoryView.layoutManager = LinearLayoutManager(activity, LinearLayout.HORIZONTAL, false)
        categoryListAdapter = CategoryListAdapter(categories)
        categoryView.adapter = categoryListAdapter
    }

    private fun loadMoviePoster() {
        Picasso.get().load(viewModel.getMovieDetailsLiveData().value?.getFormattedPosterPath())
            .placeholder(R.drawable.ic_placeholder)
            .into(moviePosterView)
    }
}