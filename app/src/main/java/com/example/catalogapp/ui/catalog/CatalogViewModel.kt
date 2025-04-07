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
                    // DTO -> Domain 모델로 변환하면서 이미지 리소스 ID도 설정
                    val domainList = dtoList.toDomainModel(context)
                    _cafeList.value = domainList
                    _uiState.value = UiState.Success
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