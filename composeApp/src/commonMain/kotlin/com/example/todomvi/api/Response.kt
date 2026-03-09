package com.example.todomvi.api

sealed class Response<out T> {
    data class Success<T>(val result: T) : Response<T>()
    data class Error<Nothing>(val msg : String) : Response<Nothing>()

}