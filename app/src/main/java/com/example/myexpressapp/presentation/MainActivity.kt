package com.example.myexpressapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.ui.theme.MyExpressAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.InputStream

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
    Row() {
        Button(onClick = viewModel::postImage) {
            Text("Click me")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = {
            scope.launch {
                viewModel.getResponse()
            }
        }) {

        }
    }
}


class UploadStreamRequestBody(
    private val mediaType: String,
    private val inputStream: InputStream,
    private val onUploadProgress: (Int) -> Unit,
) : RequestBody() {

    override fun contentLength(): Long = inputStream.available().toLong()

    override fun contentType(): MediaType? = mediaType.toMediaTypeOrNull()

    override fun writeTo(sink: BufferedSink) {
        val contentLength = inputStream.available().toFloat()
        val buffer =
            ByteArray(DEFAULT_BUFFER_SIZE) // DEFAULT_BUFFER_SIZE constant from kotlin.io.ConstantsKt
        inputStream.use { inputStream ->
            var uploaded = 0
            var read: Int
            while (inputStream.read(buffer)
                    .also { read = it } != -1
            ) { // Reads the stream until the content ends
                sink.write(buffer, 0, read)
                uploaded += read
                onUploadProgress((100 * uploaded / contentLength).toInt())
            }
        }
    }
}
