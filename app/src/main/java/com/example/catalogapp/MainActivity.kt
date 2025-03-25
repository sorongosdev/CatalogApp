package com.example.catalogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import com.example.catalogapp.ui.theme.CatalogAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogAppTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CardEx(cardData)
                    CardEx(cardData)
                }
            }
        }
    }

    companion object {
        val cardData = CardData(
            imageUri = "https://picsum.photos/200/300",
            imageDescription = "Random Image",
            author = "Sora",
            description = "Free Developer",

            )
    }
}

@Composable
fun CardEx(cardData: CardData) {
    val placeHolderColor = Color(0xFF000000)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = cardData.imageUri,
                contentScale = ContentScale.Crop,
                contentDescription = cardData.imageDescription,
                modifier = Modifier.size(32.dp)
                    .clip(CircleShape),
                placeholder = ColorPainter(placeHolderColor),
                error = ColorPainter(placeHolderColor),
            )
            Spacer(modifier = Modifier.size(8.dp))

            Column {
                Text(
                    text = cardData.author,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = cardData.description,
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun GreetingPreview() {
    CatalogAppTheme {
        CardEx(MainActivity.cardData)
    }
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String,
)