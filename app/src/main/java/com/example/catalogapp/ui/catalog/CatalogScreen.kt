package com.example.catalogapp.ui.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val cafeList by viewModel.cafeList.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is CatalogViewModel.UiState.Loading -> {
                // 로딩 중 표시
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is CatalogViewModel.UiState.Success -> {
                // 성공 시 리스트 표시
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(cafeList) { cafe ->
                        CafeItem(cafe)  // 도메인 모델을 직접 전달
                    }
                }
            }

            is CatalogViewModel.UiState.Error -> {
                // 오류 시 메시지와 재시도 버튼 표시
                val errorMessage = (uiState as CatalogViewModel.UiState.Error).message

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = errorMessage)

                        Spacer(modifier = Modifier.padding(8.dp))

                        Button(onClick = { viewModel.retry() }) {
                            Text("다시 시도")
                        }
                    }
                }
            }
        }
    }
}