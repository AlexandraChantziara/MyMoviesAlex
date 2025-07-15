package com.texnognosia.mymoviesalex

import kotlinx.serialization.json.Json

val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    prettyPrint = true
}

inline fun <reified T> mapToObject(string: String?): T? {
    return string?.let { runCatching { json.decodeFromString<T>(it) }.getOrNull() }
}

inline fun <reified T> mapToString(obj: T): String {
    return runCatching { json.encodeToString(obj) }.getOrElse { if (obj is List<*>) "[]" else "" }
}