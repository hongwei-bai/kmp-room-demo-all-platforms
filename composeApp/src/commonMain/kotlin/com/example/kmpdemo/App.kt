package com.example.kmpdemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpdemo.viewmodel.HomeViewModel
import kmp_roomlab_mydemo.composeapp.generated.resources.Res
import kmp_roomlab_mydemo.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin

@Composable
@Preview
fun App() {
    val homeViewModel: HomeViewModel = getKoin().get()
    val message = homeViewModel.uiState.collectAsState()

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }

            Spacer(Modifier.height(20.dp))
            Text("Room test:")
            Button(onClick = { homeViewModel.insertRecord() }) {
                Text("Insert a record")
            }
            Button(onClick = { homeViewModel.load() }) {
                Text("Get a record")
            }
            Button(onClick = { homeViewModel.clearRecord() }) {
                Text("Clear all records")
            }
            Text(message.value, Modifier.padding(10.dp))
        }
    }
}