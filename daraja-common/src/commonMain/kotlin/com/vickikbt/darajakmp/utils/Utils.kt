package com.vickikbt.darajakmp.utils

import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/*Format current date-time to YYYYMMDDHHmmss format*/
internal fun Instant.getDarajaTimestamp(): String {
    val currentDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())

    val year = currentDateTime.year
    val month =
        if (currentDateTime.monthNumber < 10) "0${currentDateTime.monthNumber}" else currentDateTime.monthNumber
    val dayOfMonth =
        if (currentDateTime.dayOfMonth < 10) "0${currentDateTime.dayOfMonth}" else currentDateTime.dayOfMonth
    val hour = if (currentDateTime.hour < 10) "0${currentDateTime.hour}" else currentDateTime.hour
    val minutes =
        if (currentDateTime.minute < 10) "0${currentDateTime.minute}" else currentDateTime.minute
    val seconds =
        if (currentDateTime.second < 10) "0${currentDateTime.second}" else currentDateTime.second

    val timestamp = "$year$month$dayOfMonth$hour$minutes$seconds"

    return timestamp
}

// Shortcode+Passkey+Timestamp
@OptIn(InternalAPI::class)
internal fun getDarajaPassword(shortCode: String, passkey: String, timestamp: String): String {
    val password = shortCode + passkey + timestamp
    val darajaPassword = password.encodeBase64()

    return darajaPassword
}

internal fun String.getDarajaPhoneNumber(): String? {
    if (this.isBlank()) return null
    if (this.length < 11 && this.startsWith("0")) {
        return this.replaceFirst("^0".toRegex(), "254")
    }
    return if (this.length == 13 && this.startsWith("+")) this.replaceFirst(
        "^+".toRegex(),
        ""
    ) else this
}
