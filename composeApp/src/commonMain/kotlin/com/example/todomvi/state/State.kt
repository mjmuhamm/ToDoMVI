package com.example.todomvi.state

import com.example.todomvi.model.ToDoResponseItem

data class ScreenState(
    val isLoading: Boolean = false,
    var items: List<ToDoResponseItem> = emptyList(),
    val error: String? = ""
)

sealed class MainIntent {
    object FetchData: MainIntent()
    object ClearData: MainIntent()
    object RefreshData: MainIntent()
}
