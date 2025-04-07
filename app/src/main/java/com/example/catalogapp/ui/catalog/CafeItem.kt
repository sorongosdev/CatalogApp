package com.example.catalogapp.ui.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.catalogapp.R
import com.example.catalogapp.domain.model.Cafe

@Composable
fun CafeItem(itemData: Cafe) {
    val context = LocalContext.current

    // 이미지 URL에서 리소스 이름 추출 (예: "/catalog/img/lion_home" -> "lion_home")
    val imageName = itemData.imageUrl.substringAfterLast("/")

    // 리소스 ID 가져오기
    val resourceId = context.resources.getIdentifier(
        imageName, "drawable", context.packageName
    )

    // 리소스가 없는 경우 기본 이미지 사용
    val finalResourceId = if (resourceId != 0) resourceId else R.drawable.ic_launcher_background

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = finalResourceId),
                contentDescription = itemData.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.size(8.dp)
            )

            Text(
                text = itemData.title,
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = itemData.description
            )
        }
    }
}