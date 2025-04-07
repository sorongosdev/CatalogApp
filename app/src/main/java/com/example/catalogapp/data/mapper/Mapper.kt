package com.example.catalogapp.data.mapper

import android.content.Context
import com.example.catalogapp.data.dto.CafeItemDto
import com.example.catalogapp.domain.model.Cafe

fun CafeItemDto.toDomainModel(context: Context): Cafe {
    // 이미지 URL에서 리소스 이름 추출 (예: "/catalog/img/lion_home" -> "lion_home")
    val imageName = imageUrl.substringAfterLast("/")

    // 리소스 ID 가져오기
    val resourceId = context.resources.getIdentifier(
        imageName, "drawable", context.packageName
    )

    return Cafe(
        imageUrl = imageUrl,
        title = title,
        description = description,
        imageResourceId = resourceId
    )
}

fun List<CafeItemDto>.toDomainModel(context: Context): List<Cafe> {
    return map { it.toDomainModel(context) }
}