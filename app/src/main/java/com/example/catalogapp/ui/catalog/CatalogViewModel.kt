package com.example.catalogapp.ui.catalog

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalogapp.data.mapper.toDomainModel
import com.example.catalogapp.data.repository.MainRepository
import com.example.catalogapp.domain.model.Cafe
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: MainRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    // UI 상태
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // 카페 리스트 데이터
    private val _cafeList = MutableStateFlow<List<Cafe>>(emptyList())
    val cafeList: StateFlow<List<Cafe>> = _cafeList.asStateFlow()

    // UI 상태 정의
    sealed class UiState {
        object Loading : UiState()
        object Success : UiState()
        data class Error(val message: String) : UiState()
    }

    // 초기화 시 데이터 로드
    init {
        loadCafeList()
    }

    // 카페 목록 로드
    fun loadCafeList() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            repository.getAllCatalog().fold(
                onSuccess = { dtoList ->
                    // DTO -> Domain 모델로 변환
                    val initialDomainList = dtoList.toDomainModel(context)

                    // 임시 리스트에 먼저 데이터를 저장하고 UI에 표시 (이미지 없이)
                    _cafeList.value = initialDomainList
                    _uiState.value = UiState.Success

                    // 각 항목에 대해 이미지 로드 시도
                    val updatedCafeList = mutableListOf<Cafe>()

                    initialDomainList.forEach { cafe ->
                        viewModelScope.launch {
                            try {
                                // 이미지 바이트 배열 가져오기 시도
                                repository.getImageBytes(cafe.imageUrl).fold(
                                    onSuccess = { imageBytes ->
                                        // 이미지 바이트가 로드되면 해당 항목 업데이트
                                        val updatedCafe = cafe.copy(imageBytes = imageBytes)

                                        // 리스트에 업데이트된 항목 추가
                                        updatedCafeList.add(updatedCafe)

                                        // 모든 항목이 처리되면 전체 리스트 업데이트
                                        if (updatedCafeList.size == initialDomainList.size) {
                                            _cafeList.value = updatedCafeList
                                        }
                                    },
                                    onFailure = { error ->
                                        // 이미지 로드 실패 시 원래 항목 유지
                                        updatedCafeList.add(cafe)

                                        // 모든 항목이 처리되면 전체 리스트 업데이트
                                        if (updatedCafeList.size == initialDomainList.size) {
                                            _cafeList.value = updatedCafeList
                                        }
                                    }
                                )
                            } catch (e: Exception) {
                                // 예외 발생 시 원래 항목 유지
                                updatedCafeList.add(cafe)

                                // 모든 항목이 처리되면 전체 리스트 업데이트
                                if (updatedCafeList.size == initialDomainList.size) {
                                    _cafeList.value = updatedCafeList
                                }
                            }
                        }
                    }
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "카페 목록을 불러오는데 실패했습니다")
                }
            )
        }
    }

    // 다시 시도
    fun retry() {
        loadCafeList()
    }
}