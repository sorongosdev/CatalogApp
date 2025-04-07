package com.example.catalogapp.domain.model

data class Cafe(
    val imageUrl: String,
    val title: String,
    val description: String,
    val imageResourceId: Int = 0
)