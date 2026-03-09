package com.example.todomvi.repository

import com.example.todomvi.api.Response
import com.example.todomvi.model.ToDoResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType

class ToDoARepositoryImpl(private val client : HttpClient) : ToDoApiService {
    override suspend fun getTodos(): List<ToDoResponseItem> {
        try {

            val todos =  client.get("${BASE_URL}users") {
                accept(ContentType.Application.Json)
            }.body<List<ToDoResponseItem>>()


            return todos

        } catch (e: Exception) {
            return emptyList()
        }
    }
}