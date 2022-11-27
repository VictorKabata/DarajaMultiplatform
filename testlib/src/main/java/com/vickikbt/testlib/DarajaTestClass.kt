package com.vickikbt.testlib

import com.vickikbt.darajakmp.Daraja
import com.vickikbt.darajakmp.network.models.DarajaToken
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main() {
    Napier.base(DebugAntilog())

    val daraja: Daraja = Daraja(
        consumerKey = "NrF3UW9YCIUeTeLeamBC9HRjlaGkw6RZ",
        consumerSecret = "lARzdAdZaRAtrXZ0"
    )

    var token: DarajaToken? = null

    CoroutineScope(Dispatchers.Default).launch {
        token = daraja.requestAuthToken()

        Napier.i(tag = "UI", message = "Auth token: $token")
    }

    Napier.i(tag = "UI", message = "Auth token: $token")
}