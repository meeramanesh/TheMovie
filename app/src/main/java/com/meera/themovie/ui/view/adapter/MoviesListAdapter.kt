package com.meera.themovie.ui.view.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meera.themovie.R
import com.meera.themovie.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

/**
 * MovieAdapter to bind the data in the recyclerview
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */

class MoviesListAdapter(private val activity: Activity) : RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {
    var movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(v);

    }

    fun setItems(movies: List<Movie>) {
        val startPosition = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeChanged(startPosition, movies.size)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun getItem(position: Int): Movie {
        return movies[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.txtName?.text = activity.getString(R.string.title) + movie.title
        holder.txtTitle?.text = activity.getString(R.string.language) + movie.original_language
        holder.txtReleaseDate?.text = activity.getString(R.string.releaseDate) + movie.release_date
        Picasso.get().load(movie.getFormattedPosterPath())
            .placeholder(R.drawable.ic_placeholder)
            .into(holder.movieImageView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.txtName
        val txtTitle = itemView.txtTitle
        val txtReleaseDate = itemView.txtReleaseDate
        val movieImageView = itemView.movieImageView

    }

}
