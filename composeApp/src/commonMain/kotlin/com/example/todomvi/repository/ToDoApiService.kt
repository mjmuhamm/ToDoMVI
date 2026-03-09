package com.example.todomvi.repository

import com.example.todomvi.api.Response
import com.example.todomvi.model.ToDoResponseItem

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
interface ToDoApiService {
    suspend fun getTodos() : List<ToDoResponseItem>
}