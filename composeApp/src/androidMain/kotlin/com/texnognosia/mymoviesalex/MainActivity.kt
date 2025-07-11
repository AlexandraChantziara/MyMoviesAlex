package com.texnognosia.mymoviesalex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mymoviesalex.composeapp.generated.resources.Res
import mymoviesalex.composeapp.generated.resources.androidparty
import mymoviesalex.composeapp.generated.resources.movie1
import org.jetbrains.compose.resources.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
@Composable
fun GreetingImage(message: String, from: String,modifier: Modifier) {
    val image = painterResource(Res.drawable.androidparty)
    val image1 = painterResource(Res.drawable.movie1)
    Image(
        painter = image,
        contentDescription = null
    )
}