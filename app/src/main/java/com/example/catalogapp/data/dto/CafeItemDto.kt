package com.example.catalogapp.data.dto

import com.google.gson.annotations.SerializedName

data class CafeItemDto(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)