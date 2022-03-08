package com.example.myexpressapp.data

import com.example.myexpressapp.data.dto.DtoPostImageResponse
import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.data.dto.DtoResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface MyApi {
    @GET("/")
    suspend fun getResponse(): DtoResponse

    @Multipart
    @POST("/upload")
    suspend fun postImage(
        @Part
        image: MultipartBody.Part
    ) : Response<DtoPostImageResponse>
}