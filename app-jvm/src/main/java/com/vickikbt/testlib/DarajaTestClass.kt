package com.vickikbt.testlib

import com.vickikbt.darajakmp.Daraja
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    Napier.base(DebugAntilog())

    Napier.i("Starting JVM app...")

    val daraja = Daraja(
        consumerKey = "NrF3UW9YCIUeTeLeamBC9HRjlaGkw6RZ",
        consumerSecret = "lARzdAdZaRAtrXZ0"
    )

    val token= runBlocking {
        daraja.requestAuthToken()
    }

    println("Auth token: $token")

    Napier.i(tag = "UI inside", message = "Auth token: $token")

}