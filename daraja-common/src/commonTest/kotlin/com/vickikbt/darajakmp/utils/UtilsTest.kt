package com.vickikbt.darajakmp.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

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
}
