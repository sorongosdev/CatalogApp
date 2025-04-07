package com.example.catalogapp.data.repository

import com.example.catalogapp.data.api.CatalogApiService
import com.example.catalogapp.data.dto.CafeItemDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import java.net.URL
import java.net.HttpURLConnection

interface MainRepository {
    suspend fun getAllCatalog(): Result<List<CafeItemDto>>
    suspend fun getImageBytes(imageUrl: String): Result<ByteArray>
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

    // 이미지를 바이트 배열로 가져오는 구현 추가
    override suspend fun getImageBytes(imageUrl: String): Result<ByteArray> = withContext(Dispatchers.IO) {
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val inputStream = connection.inputStream
            val bytes = inputStream.readBytes()
            inputStream.close()

            Result.success(bytes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}