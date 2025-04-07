package com.example.catalogapp.data.api

import com.example.catalogapp.data.dto.CafeItemDto
import retrofit2.Response
import retrofit2.http.GET

interface CatalogApiService {
    @GET("catalog")
    suspend fun getCatalog(): Response<List<CafeItemDto>>
}