package com.example.myexpressapp.domain.repository

import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.data.dto.DtoResponse
import com.example.myexpressapp.data.dto.DtoResponseItem
import retrofit2.Response

interface MyRepo {
    suspend fun getResponse() : DtoResponse

    suspend fun pushResponse(dtoResponse: ArrayList<DtoPostObject>) : Response<ArrayList<DtoPostObject>>
}