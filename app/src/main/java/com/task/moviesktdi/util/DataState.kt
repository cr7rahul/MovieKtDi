package com.task.moviesktdi.util

import java.lang.Exception

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val ex: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}