package com.andres.rangel.vitalyn.core.util

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error<out T>(val message: String, val data: T? = null) : DataState<T>()
    object Loading : DataState<Nothing>()
}