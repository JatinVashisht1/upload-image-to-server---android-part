package com.example.myexpressapp.data

import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.data.dto.DtoResponse
import com.example.myexpressapp.data.dto.DtoResponseItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {
    @GET("/")
    suspend fun getResponse(): DtoResponse

    @POST("/postdata")
    suspend fun postResponse(
        @Body data: ArrayList<DtoPostObject>
    ): Response<ArrayList<DtoPostObject>>
}