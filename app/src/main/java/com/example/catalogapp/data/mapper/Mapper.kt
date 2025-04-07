package com.example.catalogapp.data.mapper

import android.content.Context
import com.example.catalogapp.data.dto.CafeItemDto
import com.example.catalogapp.domain.model.Cafe

fun CafeItemDto.toDomainModel(context: Context): Cafe {
    return Cafe(
        imageUrl = imageUrl,
        title = title,
        description = description,
    )
}

fun List<CafeItemDto>.toDomainModel(context: Context): List<Cafe> {
    return map { it.toDomainModel(context) }
}