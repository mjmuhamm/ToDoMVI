package com.example.todomvi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todomvi.state.MainIntent
import com.example.todomvi.viewModel.ToDoViewModel
import com.example.todomvi.viewModel.toDoModelFactory
import org.jetbrains.compose.resources.painterResource

import todomvi.composeapp.generated.resources.Res
import todomvi.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(true) }
        val viewModel = viewModel<ToDoViewModel>(factory = toDoModelFactory)
        Column(
            modifier = Modifier
                .background(Color.White)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            val state = viewModel.state.collectAsStateWithLifecycle()




            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {


                    Column(Modifier.padding(16.dp)) {
                        Button(onClick = { viewModel.processIntent(MainIntent.FetchData) }, shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.width(130.dp)) {
                            Text("Get Data")
                        }

                        Spacer(modifier = Modifier.height((8.dp)))
                        Button(onClick = { viewModel.processIntent(MainIntent.ClearData) }, shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.width(130.dp)) {
                            Text("Clear Data")
                        }

                        Spacer(modifier = Modifier.height((8.dp)))
                        Button(onClick = { viewModel.processIntent(MainIntent.RefreshData) }, shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.width(130.dp)) {
                            Text("Refresh Data")
                        }
                    }



                    Spacer(modifier = Modifier.height((8.dp)))
                    if (state.value.isLoading) {
                        CircularProgressIndicator()
                    }

                    LazyColumn {
                        items(state.value.items) { item ->

                            Row(Modifier.padding(start = 8.dp, top = 8.dp)) {
                                Text("Name:")
                                Text(
                                    item.name,
                                    modifier = Modifier.padding(start = 8.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(Modifier.padding(start = 8.dp)) {
                                Text("ID:")
                                Text(
                                    "${item.id}",
                                    modifier = Modifier.padding(start = 8.dp),
                                )
                            }
                            Row(Modifier.padding(start = 8.dp)) {
                                Text("Email:")
                                Text(
                                    item.email,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                            Row(Modifier.padding(start = 8.dp)) {
                                Text("Address:")
                                Text(
                                    "${item.address.street}, ${item.address.city}",
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height((4.dp)))


                        }
                    }


                }
            }
        }
    }
}