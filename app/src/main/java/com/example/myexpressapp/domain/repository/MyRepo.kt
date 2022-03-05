package com.example.myexpressapp.domain.repository

import android.graphics.Bitmap
import com.example.myexpressapp.data.dto.DtoFileUpload
import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.data.dto.DtoResponse
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.InputStream

interface MyRepo {
    suspend fun postImage(inputStream: Bitmap)
    suspend fun getResponse() : DtoResponse

}