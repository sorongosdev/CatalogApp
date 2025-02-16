package com.example.catalogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.catalogapp.ui.theme.CatalogAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogAppTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.wall),
            contentDescription = "엔텔로프 캐년"
        )

        Image(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Setting"
        )
    }
}



@Preview(
    showBackground = true,
)
@Composable
fun GreetingPreview() {
    CatalogAppTheme {
        Greeting()
    }
}