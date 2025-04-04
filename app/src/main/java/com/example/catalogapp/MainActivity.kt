package com.example.catalogapp

import com.example.catalogapp.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catalogapp.ui.theme.CatalogAppTheme
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CatalogEx(items)
                }
            }
        }
    }
}

@Composable
fun Item(itemData: ItemData) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = itemData.imageId),
                contentDescription = itemData.title,
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

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    CatalogAppTheme {
        Item(
            ItemData(
                imageId = R.drawable.lion_home,
                title = "꾸러기의 집",
                description = "여기가 진짜 작업 맛집입니다... 여길 견줄 곳은 없음 진짜로. 조명도 아름답고 사람도 좋아요..^^. 여기 오시면 절대로 후회 안 함. 능률이 305% 올라감요."
            )
        )
    }
}

@Composable
fun CatalogEx(itemList: List<ItemData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(itemList){ item ->
            Item(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogExPreview() {
    CatalogEx(
        items
    )
}

data class ItemData(
    val imageId: Int,
    val title: String,
    val description: String,
)

val items = listOf(
    ItemData(
        imageId = R.drawable.lion_home,
        title = "사자의 집",
        description = "여기가 진짜 작업 맛집입니다... 여길 견줄 곳은 없음 진짜로. 조명도 아름답고 사람도 좋아요..^^. 여기 오시면 절대로 후회 안 함. 능률이 305% 올라감요."
    ),
    ItemData(
        imageId = R.drawable.urban_place,
        title = "카페 도시 외곽",
        description = "여기 카페는 고양이도 코딩해요. 가끔 나보다 코딩 잘 하는 것 같아서 기죽음. 알고리즘 문제 풀이 대결에서 거하게 질 자신있습니다!!!"
    ),
    ItemData(
        imageId = R.drawable.songdo,
        title = "송도 솔찬공원 앞 카페",
        description = "무조건! 해질녘에 가셔야 합니다. 또닥 또닥, 조금만 코딩하다보면 어느새 뉘엿뉘엿 해가 지는 게 보이는데... 석양이 진다... 그리고 내 마음을 저격한다 (탕탕)🔫"
    )
)