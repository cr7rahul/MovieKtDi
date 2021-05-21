package com.task.moviesktdi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.moviesktdi.R
import com.task.moviesktdi.databinding.FragmentMovieListBinding
import com.task.moviesktdi.model.list.MovieListItem
import com.task.moviesktdi.ui.adapter.MovieListAdapter
import com.task.moviesktdi.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentMovieListBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private var adapter: MovieListAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding = DataBindingUtil.bind(view)!!

        subscribeObservers()
        movieViewModel.setStateEvent(
            MovieStateEvent.GetMovieList, 1, 1, "98084b20057fd489145e225e6cba4025"
        )

        adapter = MovieListAdapter(
            arrayListOf(), MovieListAdapter.OnItemClick { id ->
                val bundle = bundleOf("id" to id)
                navController.navigate(R.id.movieDetailsFragment, bundle)
            }
        )
        binding.movieList.layoutManager = LinearLayoutManager(context)
        binding.movieList.adapter = adapter

    }

    private fun subscribeObservers() {
        movieViewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                is DataState.Success<List<MovieListItem>> -> {
                    binding.progressBar.visibility = View.GONE
                    retrieveMovieList(dataState.data)
                }
            }
        })
    }

    private fun retrieveMovieList(data: List<MovieListItem>) {
        adapter?.apply {
            addItems(data)
            notifyDataSetChanged()
        }
    }

}