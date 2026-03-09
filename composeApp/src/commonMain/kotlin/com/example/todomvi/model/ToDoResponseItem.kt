package com.example.todomvi.model

import kotlinx.serialization.Serializable

@Serializable
data class ToDoResponseItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)