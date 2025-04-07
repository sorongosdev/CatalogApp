package com.example.catalogapp.data.repository

import com.example.catalogapp.data.api.CatalogApiService
import com.example.catalogapp.data.dto.CafeItemDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface MainRepository {
    suspend fun getAllCatalog(): Result<List<CafeItemDto>>
}

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val apiService: CatalogApiService
) : MainRepository {

    override suspend fun getAllCatalog(): Result<List<CafeItemDto>> = withContext(Dispatchers.IO){
        try {
            val response = apiService.getCatalog()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("응답 본문이 비어 있음"))
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}