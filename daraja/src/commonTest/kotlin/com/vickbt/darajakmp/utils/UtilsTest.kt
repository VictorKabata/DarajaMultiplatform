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
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UtilsTest {

    @Test
    fun `getDarajaTimeStamp returns correct timestamp on single digit values`() {
        val currentDateTime = "2022-01-01T01:01:01.694394300".toLocalDateTime()
            .toInstant(TimeZone.currentSystemDefault())
        val expectedResult = "20220101010101"

        assertEquals(expected = expectedResult, actual = currentDateTime.getDarajaTimestamp())
    }

    @Test
    fun `getDarajaTimeStamp returns correct timestamp on double digit values`() {
        val currentDateTime = "2022-12-12T12:12:12.694394300".toLocalDateTime()
            .toInstant(TimeZone.currentSystemDefault())
        val expectedResult = "20221212121212"

        assertEquals(expected = expectedResult, actual = currentDateTime.getDarajaTimestamp())
    }

    @Test
    fun `phone number starting with 07 is formatted correctly`() {
        val phoneNumber = "0714021306"
        val expectedResult = "254714021306"

        assertEquals(expected = expectedResult, actual = phoneNumber.getDarajaPhoneNumber())
    }

    @Test
    fun `phone number starting with 01 is formatted correctly`() {
        val phoneNumber = "0114624401"
        val expectedResult = "254114624401"

        assertEquals(expected = expectedResult, actual = phoneNumber.getDarajaPhoneNumber())
    }

    @Test
    fun `phone number starting with 254 is formatted correctly`() {
        val phoneNumber = "254714091301"
        val expectedResult = "254714091301"

        assertEquals(expected = expectedResult, actual = phoneNumber.getDarajaPhoneNumber())
    }

    @Test
    fun `phone number starting with plus 254 is formatted correctly`() {
        val phoneNumber = "+254714091301"
        val expectedResult = "254714091301"

        assertEquals(expected = expectedResult, actual = phoneNumber.getDarajaPhoneNumber())
    }

    @Test
    fun `phone number starting with 07 and less than 10 characters throws errors`() {
        val phoneNumber = "071409130"

        assertFailsWith<DarajaException> {
            phoneNumber.getDarajaPhoneNumber()
        }
    }

    @Test
    fun `phone number starting with 254 and less than 10 characters throws errors`() {
        val phoneNumber = "25471409130"

        assertFailsWith<DarajaException> { phoneNumber.getDarajaPhoneNumber() }
    }

    @Test
    fun `phone number starting with plus 254 and less than 10 characters throws errors`() {
        val phoneNumber = "+25471409130"

        assertFailsWith<DarajaException> { phoneNumber.getDarajaPhoneNumber() }
    }

    @Test
    fun `phone number starting with 07 and more than 10 characters throws errors`() {
        val phoneNumber = "07140913023"

        assertFailsWith<DarajaException> { phoneNumber.getDarajaPhoneNumber() }
    }

    @Test
    fun `phone number starting with 254 and more than 10 characters throws errors`() {
        val phoneNumber = "2547140913023"

        assertFailsWith<DarajaException> { phoneNumber.getDarajaPhoneNumber() }
    }

    @Test
    fun `phone number starting with plus 254 and more than 10 characters throws errors`() {
        val phoneNumber = "+2547140913023"

        assertFailsWith<DarajaException> { phoneNumber.getDarajaPhoneNumber() }
    }

    @Test
    fun `phone number starting with 07 and non digit characters throws errors`() {
        val phoneNumber = "071409130r"

        assertFailsWith<DarajaException> { phoneNumber.getDarajaPhoneNumber() }
    }

    @Test
    fun `phone number starting with 254 and non digit characters throws errors`() {
        val phoneNumber = "+25471401?023"

        assertFailsWith<DarajaException> { phoneNumber.getDarajaPhoneNumber() }
    }

    @Test
    fun `phone number starting with plus 254 and non digit characters throws errors`() {
        val phoneNumber = "25Z7140930."

        assertFailsWith<DarajaException> { phoneNumber.getDarajaPhoneNumber() }
    }

    @Test
    fun `phone number with spaces are formatted correctly`() {
        val phoneNumbers = listOf("0 714091 30  3", " + 2 547  140 913 03", "2 5 4714 091 30 3 ")
        val expectedPhoneNumbers = listOf("254714091303", "254714091303", "254714091303")

        assertEquals(expectedPhoneNumbers, phoneNumbers.map { it.getDarajaPhoneNumber() })
    }

    @Test
    fun `time units with value less than 10 are formatted correctly`() {
        val timeUnit = 1
        val expectedTimeUnit = "01"

        assertEquals(expected = expectedTimeUnit, actual = timeUnit.asFormattedWithZero())
    }

    @Test
    fun `time units with value more than 10 retain their formatting`() {
        val timeUnit = 11
        val expectedTimeUnit = 11

        assertEquals(expected = expectedTimeUnit, actual = timeUnit.asFormattedWithZero())
    }
}
