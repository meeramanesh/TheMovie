package com.meera.themovie.ui.fragment

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.android.databinding.library.baseAdapters.BR
import com.meera.themovie.R
import com.meera.themovie.configuration.Constants
import com.meera.themovie.data.model.Movie
import com.meera.themovie.data.model.MovieApiResponse
import com.meera.themovie.databinding.FragmentMovieListBinding
import com.meera.themovie.ui.base.BaseFragment
import com.meera.themovie.ui.view.adapter.MoviesListAdapter
import com.meera.themovie.ui.view.recyclerview.RecyclerItemClickListener
import com.meera.themovie.ui.view.recyclerview.RecyclerViewPaginator
import com.meera.themovie.ui.viewmodel.MovieListViewModel
import com.meera.themovie.util.NavUtils
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * Fragment to display the Movie list in recycler view.
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */

class MovieListFragment : BaseFragment<MovieListViewModel, FragmentMovieListBinding>(),
    RecyclerItemClickListener.OnRecyclerViewItemClickListener {


    companion object {
        const val REQUEST_CODE = 1001
    }

    private lateinit var moviesListAdapter: MoviesListAdapter

    /**
     * boolean to identify the list is filtered or complete
     */
    private var isFilteredList = false

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun getViewModel(): Class<MovieListViewModel> {
        return MovieListViewModel::class.java
    }

    override fun getTitle(): String {
        return getString(R.string.popular_movies)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_movie_list


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
        observerMovieListResponse()
    }

    //observing the movie list response from server
    private fun observerMovieListResponse() {
        viewModel.getMoviesLiveData().observe(this, Observer {
            updateMoviesList(it!!)
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFilteredList = false
        initialiseView()
    }

    /**
     * Initialising the adapter for move list
     */

    private fun initialiseView() {
        moviesListAdapter = MoviesListAdapter(activity)

        moviesList.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        moviesListAdapter = MoviesListAdapter(activity)
        moviesList.adapter = moviesListAdapter

        moviesList.addOnItemTouchListener(RecyclerItemClickListener(requireContext(), this))

        moviesList.addOnScrollListener(object : RecyclerViewPaginator(moviesList) {
            override val isLastPage: Boolean
                get() = viewModel.isLastPage()

            override fun loadMore(page: Long) {
                if (!isFilteredList) {
                    viewModel.loadMovieList(page)
                }

            }

            override fun loadFirstData(page: Long) {
                if (!isFilteredList) {
                    viewModel.loadMovieList(page)
                }
            }
        })
    }

    /**
     * Updating the movie list from Server
     */
    private fun updateMoviesList(movieApiResponse: MovieApiResponse) {
        moviesListAdapter.setItems(movieApiResponse.results)
    }

    /**
     * Updating the Filtered Movie list
     */
    private fun updateFilteredMoviesList(results: List<Movie>) {
        if (results.isEmpty()) {
            moviesListAdapter.movies.clear()
            emptyContainer.visibility = View.VISIBLE
            moviesList.visibility = View.GONE

        } else {
            emptyContainer.visibility = View.GONE
            moviesList.visibility = View.VISIBLE
            moviesListAdapter.setItems(results)
            moviesList.adapter = moviesListAdapter
            moviesListAdapter.notifyDataSetChanged()
        }

    }

    /**
     * Pushing the details fragment on selecting a movie.
     */
    override fun onItemClick(parentView: View, childView: View, position: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.MOVIE_ID, moviesListAdapter.getItem(position).id)
        NavUtils.pushMovieDetailsFragment(bundle)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Filter options menu will open the dialog fragment to select the filtering dates
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {

        R.id.action_filter -> {
            val filterDialog = FilterDialogFragment()
            filterDialog.setTargetFragment(this, REQUEST_CODE);
            filterDialog.show(getActivity()?.supportFragmentManager, "")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    /**
     * This method will return the from and To year which has been selected from tehDialogFragment
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MovieListFragment.REQUEST_CODE) {

            if (resultCode == Constants.RESULT_CODE_OK) {
                var fromYear = data?.getStringExtra(Constants.FROM_YEAR)
                var toYear = data?.getStringExtra(Constants.TO_YEAR)
                val results: List<Movie>? = viewModel.getMoviesLiveData().value?.results
                val filteredItems = results?.filter { filterWithYear(it, fromYear!!, toYear!!) }
                isFilteredList = true
                updateFilteredMoviesList(filteredItems!!)
            } else {
                isFilteredList = false
                updateFilteredMoviesList(viewModel.getMoviesLiveData().value?.results!!)
            }

        }
    }

    /**
     * Method to filter with Year
     */
    private fun filterWithYear(item: Movie, fromYear: String, toYear: String): Boolean {
        var year = item.release_date.substring(0, item.release_date.indexOf("-"))
        if (year.toInt() >= fromYear.toInt() && year.toInt() <= toYear.toInt()) {
            return true
        }
        return false
    }


}