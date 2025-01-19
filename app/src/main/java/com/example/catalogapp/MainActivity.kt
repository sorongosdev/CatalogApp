package com.example.catalogapp

import android.os.Bundle
import android.view.RoundedCorner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catalogapp.ui.theme.CatalogAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogAppTheme {
                Greeting(onButtonClicked = {
                    Toast.makeText(this,"Send Clicked", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

@Composable
fun Greeting(onButtonClicked: () -> Unit) {
    Button(
        onClick = onButtonClicked,
        enabled = true,
        border = BorderStroke(10.dp, Color.Magenta),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(20.dp)
    ){
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "Send")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CatalogAppTheme {
        Greeting(onButtonClicked={})
    }
}