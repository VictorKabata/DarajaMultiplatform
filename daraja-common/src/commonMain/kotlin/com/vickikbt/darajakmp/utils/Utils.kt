package com.vickikbt.darajakmp.utils

import io.github.aakira.napier.Napier
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/*Format current date-time to YYYYMMDDHHmmss format*/
internal fun getDarajaTimestamp(): String {
    val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val year = currentDateTime.year
    val month = currentDateTime.monthNumber
    val dayOfMonth = currentDateTime.dayOfMonth
    val hour = currentDateTime.hour
    val minutes = currentDateTime.minute
    val seconds = currentDateTime.second

    val timestamp = "$year$month$dayOfMonth$hour$minutes$seconds"
    return timestamp
}

// Shortcode+Passkey+Timestamp
@OptIn(InternalAPI::class)
fun getDarajaPassword(shortCode: String, passkey: String, timestamp: String): String {
    val password = shortCode + passkey + timestamp
    val darajaPassword = password.encodeBase64()

    Napier.i("Daraja password: $darajaPassword")

    return darajaPassword
}