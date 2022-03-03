package com.example.myexpressapp.data.tempdata


import com.google.gson.annotations.SerializedName

data class TempDataItem(
    @SerializedName("address")
    val address: String = "",
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("name")
    val name: String = ""
)