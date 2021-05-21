package com.task.moviesktdi.ui

import androidx.lifecycle.*
import com.task.moviesktdi.model.details.MovieDetailsItem
import com.task.moviesktdi.model.list.MovieListItem
import com.task.moviesktdi.repository.MovieRepository
import com.task.moviesktdi.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<MovieListItem>>> = MutableLiveData()
    private val _movieDetailsDataState: MutableLiveData<DataState<MovieDetailsItem>> =
        MutableLiveData()

    val dataState: LiveData<DataState<List<MovieListItem>>>
        get() = _dataState

    val movieDetailsDataState: LiveData<DataState<MovieDetailsItem>>
        get() = _movieDetailsDataState

    fun setStateEvent(movieStateEvent: MovieStateEvent, list_id: Int, page: Int, apiKey: String) {
        viewModelScope.launch {
            when (movieStateEvent) {
                is MovieStateEvent.GetMovieList -> {
                    movieRepository.retrieveMovieList(list_id, page, apiKey)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MovieStateEvent.None -> {

                }
            }
        }
    }

    fun setMovieDetailsEvent(
        movieDetailsStateEvent: MovieDetailsStateEvent,
        movie_id: Int,
        api_key: String,
        language: String
    ) {
        viewModelScope.launch {
            when (movieDetailsStateEvent) {
                is MovieDetailsStateEvent.GetMovieDetails -> {
                    movieRepository.retrieveMovieDetails(movie_id, api_key, language)
                        .onEach { dataState ->
                            _movieDetailsDataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MovieDetailsStateEvent.None -> {

                }
            }
        }
    }
}


sealed class MovieStateEvent {
    object GetMovieList : MovieStateEvent()
    object None : MovieStateEvent()
}

sealed class MovieDetailsStateEvent {
    object GetMovieDetails : MovieDetailsStateEvent()
    object None : MovieDetailsStateEvent()
}