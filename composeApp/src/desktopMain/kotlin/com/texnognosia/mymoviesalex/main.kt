package com.texnognosia.mymoviesalex

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import mymoviesalex.composeapp.generated.resources.Res
import mymoviesalex.composeapp.generated.resources.androidparty
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.Image
import com.texnognosia.mymoviesalex.koin.KoinInit
import mymoviesalex.composeapp.generated.resources.movie1

fun main() = application {
    KoinInit().init()
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyMoviesAlex",
    ) {
        App()
    }
}

@Composable
fun GreetingImage(message: String, from: String,modifier: Modifier ) {
    val image = painterResource(Res.drawable.androidparty)
    val image1 = painterResource(Res.drawable.movie1)
    Image(
        painter = image,
        contentDescription = null
    )
}