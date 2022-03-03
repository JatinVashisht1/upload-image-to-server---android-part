package com.example.myexpressapp.data.repository

import android.util.Log
import com.example.myexpressapp.data.MyApi
import com.example.myexpressapp.data.dto.DtoPostObject
import com.example.myexpressapp.data.dto.DtoResponse
import com.example.myexpressapp.data.dto.DtoResponseItem
import com.example.myexpressapp.domain.repository.MyRepo
import retrofit2.Response

class MyRepoImpl(val api: MyApi) : MyRepo {
    override suspend fun getResponse(): DtoResponse {
        Log.d("HomeScreen", "data is "+api.getResponse().toString())
        return api.getResponse()
    }

    override suspend fun pushResponse(dtoResponse: ArrayList<DtoPostObject>): Response<ArrayList<DtoPostObject>> {
        return api.postResponse(data = dtoResponse)
    }
}