package com.task.moviesktdi.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.task.moviesktdi.R
import com.task.moviesktdi.databinding.FragmentMovieDetailsBinding
import com.task.moviesktdi.model.details.MovieDetailsItem
import com.task.moviesktdi.util.Constants
import com.task.moviesktdi.util.DataState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var navController: NavController
    private val movieViewModel: MovieViewModel by viewModels()
    private var movieId = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding = DataBindingUtil.bind(view)!!

        movieId = requireArguments().getInt("id")

        subscribeObservers()
        movieViewModel.setMovieDetailsEvent(
            MovieDetailsStateEvent.GetMovieDetails,
            movieId,
            "98084b20057fd489145e225e6cba4025",
            "en-US"
        )
    }

    private fun subscribeObservers() {
        movieViewModel.movieDetailsDataState.observe(viewLifecycleOwner, { movieDataState ->
            when (movieDataState) {
                is DataState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataState.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
                is DataState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    retrieveDetails(movieDataState.data)
                }
            }
        })
    }

    private fun retrieveDetails(data: MovieDetailsItem) {
        binding.lblTitle.text = data.original_title
        binding.overview.text = data.overview
        binding.rating.text = data.vote_average.toString()
        Glide.with(binding.root)
            .load(Constants.MOVIE_IMAGE_PNG + data.backdrop_path)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.imgMovieImage)
    }
}