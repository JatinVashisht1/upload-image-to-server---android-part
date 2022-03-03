package com.example.myexpressapp.data.dto


import com.google.gson.annotations.SerializedName

data class DtoResponseItem(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("name")
    val name: String = ""
)