package com.example.myexpressapp.data.repository

import android.R.attr.bitmap
import android.app.Application
import android.graphics.Bitmap
import android.os.Environment
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.example.myexpressapp.R
import com.example.myexpressapp.data.MyApi
import com.example.myexpressapp.data.dto.DtoResponse
import com.example.myexpressapp.domain.repository.MyRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream


class MyRepoImpl(
    val api: MyApi,
    @ApplicationContext
    val application: Application
) : MyRepo {

    override suspend fun getResponse(): DtoResponse {
        return api.getResponse()
    }

    override suspend fun postImage(stream: Bitmap) {
        CoroutineScope(IO).launch {

            val bos = ByteArrayOutputStream()
            stream.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()
            val request =  RequestBody.create("image/*".toMediaTypeOrNull(), bitmapdata)  // read all bytes using kotlin extension
            val filePart = MultipartBody.Part.createFormData(
                    "file",
                    "eminem.jpg",
                    request
                )


            try {
                if (filePart != null) {
                    api.postImage(filePart)
                }
            }
            catch (e: Exception) {
//                Snackbar.make(viewBinding.root, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                return@launch
            }
            Log.d("MyActivity", "on finish upload file")
            val result = api.postImage(filePart)
            Log.d("responsephoto", "Response is ${result.toString()}")
        }
    }
}
