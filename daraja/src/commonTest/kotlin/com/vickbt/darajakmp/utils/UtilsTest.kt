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

import assertk.all
import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.vickbt.darajakmp.network.models.DarajaException
import io.ktor.util.decodeBase64String
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.test.Test

class UtilsTest {
    @Test
    fun `getDarajaTimeStamp returns correct timestamp on single digit values`() =
        runTest {
            val currentDateTime =
                LocalDateTime.parse("2022-01-01T01:01:01.694394300")
                    .toInstant(TimeZone.currentSystemDefault())
            val expectedResult = "20220101010101"

            assertThat(currentDateTime.getDarajaTimestamp()).isEqualTo(expectedResult)
        }

    @Test
    fun `getDarajaTimeStamp returns correct timestamp on double digit values`() =
        runTest {
            val currentDateTime =
                LocalDateTime.parse("2022-12-12T12:12:12.694394300")
                    .toInstant(TimeZone.currentSystemDefault())
            val expectedResult = "20221212121212"

            assertThat(currentDateTime.getDarajaTimestamp()).isEqualTo(expectedResult)
        }

    @Test
    fun `getDarajaPassword returns correct password`() =
        runTest {
            val darajaPassword = getDarajaPassword("abc", "123", "xyz")
            val result = "abc" + "123" + "xyz"

            assertThat(darajaPassword.decodeBase64String()).isEqualTo(result)
        }

    @Test
    fun `phone number starting with 07 is formatted correctly`() =
        runTest {
            val phoneNumber = "0714021306"
            val expectedResult = "254714021306"

            assertThat(phoneNumber.getDarajaPhoneNumber()).isEqualTo(expectedResult)
        }

    @Test
    fun `phone number starting with 01 is formatted correctly`() =
        runTest {
            val phoneNumber = "0114624401"
            val expectedResult = "254114624401"

            assertThat(phoneNumber.getDarajaPhoneNumber()).isEqualTo(expectedResult)
        }

    @Test
    fun `phone number starting with 254 is formatted correctly`() =
        runTest {
            val phoneNumber = "254714091301"
            val expectedResult = "254714091301"

            assertThat(phoneNumber.getDarajaPhoneNumber()).isEqualTo(expectedResult)
        }

    @Test
    fun `phone number starting with plus 254 is formatted correctly`() =
        runTest {
            val phoneNumber = "+254714091301"
            val expectedResult = "254714091301"

            assertThat(phoneNumber.getDarajaPhoneNumber()).isEqualTo(expectedResult)
        }

    @Test
    fun `phone number less than 10 characters throws errors`() =
        runTest {
            val phoneNumbers = listOf("071409130", "+25471409130", "25471409130")

            assertFailure {
                phoneNumbers.forEach { phoneNumber -> phoneNumber.getDarajaPhoneNumber() }
            }
        }

    @Test
    fun `phone number more than 10 characters throws errors`() =
        runTest {
            val phoneNumbers = listOf("07140913023", "+2547140913023", "2547140913023")

            assertFailure {
                phoneNumbers.forEach { phoneNumber -> phoneNumber.getDarajaPhoneNumber() }
            }.all {
                instanceOf(DarajaException::class)
            }
        }

    @Test
    fun `phone number with spaces are formatted correctly`() =
        runTest {
            val phoneNumbers =
                listOf("0 714091 30  3", " + 2 547  140 913 03", "2 5 4714 091 30 3 ")
            val expectedPhoneNumbers = listOf("254714091303", "254714091303", "254714091303")

            assertThat(phoneNumbers.map { it.getDarajaPhoneNumber() }).isEqualTo(
                expectedPhoneNumbers,
            )
        }

    @Test
    fun `time units with value less than ten are formatted correctly`() =
        runTest {
            val timeUnit = 1
            val expectedTimeUnit = "01"

            assertThat(timeUnit.asFormattedWithZero()).isEqualTo(expectedTimeUnit)
        }

    @Test
    fun `time units with value more than ten retain their formatting`() =
        runTest {
            val timeUnit = 11
            val expectedTimeUnit = 11

            assertThat(timeUnit.asFormattedWithZero()).isEqualTo(expectedTimeUnit)
        }

    @Test
    fun `capitalize returns strings in title case`() =
        runTest {
            val wordList = listOf("heLlo", "WorlD", "DARAJA", "kmp", "DaRaJa", "2")
            val expectedWordList = listOf("Hello", "World", "Daraja", "Kmp", "Daraja", "2")

            assertThat(wordList.map { it.capitalize() }).isEqualTo(expectedWordList)
        }
}
