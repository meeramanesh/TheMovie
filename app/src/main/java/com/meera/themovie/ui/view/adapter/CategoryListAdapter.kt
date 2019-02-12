package com.meera.themovie.ui.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meera.themovie.R
import com.meera.themovie.data.model.Category
import kotlinx.android.synthetic.main.single_item_view.view.*

/**
 * MovieAdapter to bind the data in the recyclerview
 * ------------------
 * Author : meera
 * Email : meerasrikumar@gmail.com
 * Date : 2/10/2019
 * Project : TheMovie
 */

class CategoryListAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.single_item_view, parent, false)
        return ViewHolder(v);

    }


    override fun getItemCount(): Int {
        return categories.size
    }

    fun getItem(position: Int): Category {
        return categories[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.txtName?.text = category.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.itemTitle
    }

}
