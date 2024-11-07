package com.vickbt.daraja.android.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.toJson(): String {
    val json = Json {
        prettyPrint = true
    }

    return json.encodeToString(this).replaceFirst("\"","")
}