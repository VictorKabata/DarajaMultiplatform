/*
 * Copyright 2022 Daraja Multiplatform
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vickbt.darajakmp.utils

import com.vickbt.darajakmp.network.models.DarajaException
import io.ktor.util.encodeBase64
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**Format current timestamp to YYYYMMDDHHmmss format*/
internal fun Instant.getDarajaTimestamp(): String {
    val currentDateTime = this.toLocalDateTime(TimeZone.currentSystemDefault())

    val year = currentDateTime.year
    val month = currentDateTime.monthNumber.asFormattedWithZero()
    val dayOfMonth = currentDateTime.dayOfMonth.asFormattedWithZero()
    val hour = currentDateTime.hour.asFormattedWithZero()
    val minutes = currentDateTime.minute.asFormattedWithZero()
    val seconds = currentDateTime.second.asFormattedWithZero()

    return "$year$month$dayOfMonth$hour$minutes$seconds"
}

/**
 * Formats time values that have a single digit by prefixing them with an extra zero
 * e.g "1:00" becomes "01:00"
 */
internal fun Int.asFormattedWithZero(): Comparable<*> = when (this < 10) {
    true -> "0$this"
    false -> this
}

// Shortcode+Passkey+Timestamp
/** Generates a base 64 string by encoding a combination of [shortCode], [passkey] and [timestamp]*/
internal fun getDarajaPassword(shortCode: String, passkey: String, timestamp: String): String {
    val password = shortCode + passkey + timestamp

    return password.encodeBase64()
}

/**Format phone number provided by user to format that Daraja API recognises ie. 254714023125
 *
 * @return [phoneNumber] Formatted string to match a valid Safaricom/M-Pesa number
 * @throws [DarajaException]
 * */
internal fun String.getDarajaPhoneNumber(): String {
    val phoneNumber = this.replace("\\s".toRegex(), "")

    return when {
        phoneNumber.matches(Regex("^(?:254)?[17](?:\\d\\d|0[0-8]|(9[0-2]))\\d{6}\$")) -> phoneNumber
        phoneNumber.matches(Regex("^0?[17](?:\\d\\d|0[0-8]|(9[0-2]))\\d{6}\$")) ->
            phoneNumber.replaceFirst("0", "254")
        phoneNumber.matches(Regex("^(?:\\+254)?[17](?:\\d\\d|0[0-8]|(9[0-2]))\\d{6}\$")) ->
            phoneNumber.replaceFirst("+", "")
        else -> throw DarajaException("Invalid phone number format provided: $this")
    }
}
