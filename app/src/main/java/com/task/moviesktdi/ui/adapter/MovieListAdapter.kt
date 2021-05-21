package com.task.moviesktdi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.moviesktdi.R
import com.task.moviesktdi.databinding.MovieListAdapterBinding
import com.task.moviesktdi.model.list.MovieListItem
import com.task.moviesktdi.util.Constants

class MovieListAdapter(
    private val movieItems: ArrayList<MovieListItem>,
    private val clickListener: OnItemClick
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            MovieListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder: MovieListViewHolder = holder as MovieListViewHolder
        movieViewHolder.bindItems(movieItems[position], clickListener)
    }

    override fun getItemCount(): Int = movieItems.size

    fun addItems(items: List<MovieListItem>) {
        this.movieItems.apply {
            clear()
            addAll(items)
        }
    }

    class MovieListViewHolder(
        private val binding: MovieListAdapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(
            movieItems: MovieListItem,
            clickListener: OnItemClick
        ) {
            binding.clickListener = clickListener
            binding.movieItems = movieItems
            Glide.with(binding.root)
                .load(Constants.MOVIE_IMAGE_500 + movieItems.backdrop_path)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.imageView)
        }
    }

    class OnItemClick(val clickListener: (id: Int) -> Unit) {
        fun onClick(items: MovieListItem) = clickListener(items.id)
    }
}