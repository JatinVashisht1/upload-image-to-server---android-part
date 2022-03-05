package com.example.myexpressapp.data.dto


import com.google.gson.annotations.SerializedName

data class DtoPostImageResponse(
    @SerializedName("imglink")
    val imglink: String = ""
)