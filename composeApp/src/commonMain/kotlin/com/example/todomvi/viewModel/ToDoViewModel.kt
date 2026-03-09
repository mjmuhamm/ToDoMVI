package com.example.todomvi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todomvi.state.MainIntent
import com.example.todomvi.state.ScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todomvi.api.Network
import com.example.todomvi.api.Response
import com.example.todomvi.model.ToDoResponseItem
import com.example.todomvi.repository.ToDoARepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ToDoViewModel(val repository: ToDoARepositoryImpl = ToDoARepositoryImpl(Network.networkClient))  : ViewModel() {

    private var _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()

    fun processIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.ClearData -> clearData()
            MainIntent.FetchData -> fetchData()
            MainIntent.RefreshData -> refreshData()
        }
    }

    fun fetchData() {
        _state.update {
            _state.value.copy(isLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000) // simulate network call
//            _state.value = state.value.copy(
//                isLoading = false,
//                error = null,
//                items = repository.getTodos() as List<ToDoResponseItem>
//            )
            _state.update {
                val x = repository.getTodos()
                println("what im looking for ${x}")
                ScreenState(
                    items = repository.getTodos()
                )
            }
        }
    }

    fun clearData() {
        _state.update {
            ScreenState(
                items = emptyList()
            )
        }
    }

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                ScreenState(
                    items = repository.getTodos() as List<ToDoResponseItem>
                )
            }
        }
    }
}

val toDoModelFactory = viewModelFactory {
    initializer {
        ToDoViewModel()
    }
}