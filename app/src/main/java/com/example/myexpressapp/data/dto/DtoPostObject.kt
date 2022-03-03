package com.example.myexpressapp.data.dto

import com.google.gson.annotations.SerializedName

data class DtoPostObject(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("name")
    val name: String = ""
)
