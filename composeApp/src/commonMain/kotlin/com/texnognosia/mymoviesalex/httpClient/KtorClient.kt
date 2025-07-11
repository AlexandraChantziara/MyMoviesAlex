package com.texnognosia.mymoviesalex.httpClient

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

fun getKtorClient()  : HttpClient {
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }
}