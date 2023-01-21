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

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class UtilsTest {

    @Test
    fun getDarajaTimeStamp_returns_correct_timestamp_on_single_digit_values() {
        val currentDateTime = "2022-01-01T01:01:01.694394300".toLocalDateTime()
            .toInstant(TimeZone.currentSystemDefault())
        val expectedResult = "20220101010101"

        assertEquals(expected = expectedResult, actual = currentDateTime.getDarajaTimestamp())
    }

    @Test
    fun getDarajaTimeStamp_returns_correct_timestamp_on_double_digit_values() {
        val currentDateTime = "2022-12-12T12:12:12.694394300".toLocalDateTime()
            .toInstant(TimeZone.currentSystemDefault())
        val expectedResult = "20221212121212"

        assertEquals(expected = expectedResult, actual = currentDateTime.getDarajaTimestamp())
    }

    @Test
    fun phone_number_starting_with_07_is_formatted_correctly() {
        val phoneNumber = "0714091304"
        val expectedResult = "254714091304"

        assertEquals(expected = expectedResult, actual = phoneNumber.getDarajaPhoneNumber())
    }

    @Test
    fun phone_number_starting_with_254_is_formatted_correctly() {
        val phoneNumber = "254714091304"
        val expectedResult = "254714091304"

        assertEquals(expected = expectedResult, actual = phoneNumber.getDarajaPhoneNumber())
    }

    @Test
    fun time_units_with_value_less_than_ten_are_formatted_as_expected() {
        val timeUnit = 1
        val expectedTimeUnit = "01"

        assertEquals(expected = expectedTimeUnit, actual = timeUnit.asFormattedWithZero())
    }

    @Test
    fun time_units_with_value_more_than_ten_retain_their_formatting() {
        val timeUnit = 11
        val expectedTimeUnit = 11

        assertEquals(expected = expectedTimeUnit, actual = timeUnit.asFormattedWithZero())
    }
}
