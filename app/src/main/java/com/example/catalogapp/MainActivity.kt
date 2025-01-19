package com.example.catalogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Button(
        onClick = {},
        modifier = Modifier
            .size(200.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.Cyan
        ),
        shape = RectangleShape
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null
        )
        Spacer(
            modifier = Modifier.size(ButtonDefaults.IconSpacing)
        )
        Text(text = "Search")
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