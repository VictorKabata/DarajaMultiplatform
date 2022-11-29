package com.vickikbt.darajakmp.utils

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