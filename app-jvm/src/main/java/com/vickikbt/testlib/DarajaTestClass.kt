package com.vickikbt.testlib

import com.vickikbt.darajakmp.Daraja
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.runBlocking

fun main() {
    Napier.base(DebugAntilog())

    Napier.i("Starting JVM app...")

    val daraja = Daraja(
        consumerKey = "NrF3UW9YCIUeTeLeamBC9HRjlaGkw6RZ",
        consumerSecret = "lARzdAdZaRAtrXZ0"
    )

    val token = runBlocking {
        daraja.requestAuthToken()
    }

    Napier.i(tag = "UI Token", message = "Auth token: $token")

}