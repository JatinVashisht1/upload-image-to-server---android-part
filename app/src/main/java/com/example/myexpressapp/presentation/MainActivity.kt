package com.example.myexpressapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.data.dto.DtoResponseItem
import com.example.myexpressapp.ui.theme.MyExpressAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyExpressAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyUi()
                }
            }
        }
    }
}

@Composable
fun MyUi(viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.myData.value
    Log.d("HomeScreen", "value of get reques is ${state.toString()}")
    val response =
        arrayListOf<DtoPostObject>(DtoPostObject("MyAddress", "Saksham Awashthi"))
    val scope = rememberCoroutineScope()
    Box() {
        Button(onClick = { scope.launch { viewModel.pushData(response) } }) {
            Text("Click me")
        }
    }
}
