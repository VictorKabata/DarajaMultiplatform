/*
 * Copyright 2023 Daraja Multiplatform
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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class DarajaResultTest {

    private val darajaException = DarajaException(
        requestId = "43301-58413611-1",
        errorCode = "400.008.01",
        errorMessage = "Invalid Authentication passed"
    )

    @Test
    fun darajaResult_getOrNull_returns_data_on_success() {
        val result = DarajaResult.Success(data = "Success").getOrNull()

        assertNotNull(result)
        assertEquals(expected = "Success", actual = result)
    }

    @Test
    fun darajaResult_getOrNull_returns_null_on_error() {
        val result = DarajaResult.Failure(exception = darajaException).getOrNull()

        assertNull(result)
        assertEquals(expected = null, actual = result)
    }

    @Test
    fun darajaResult_throwOnFailure_returns_exception_on_error() {
        val result = DarajaResult.Failure(exception = darajaException).throwOnFailure()

        assertNotNull(result)
        assertEquals(expected = darajaException, actual = result)
    }

    @Test
    fun darajaResult_getOrThrow_returns_data_on_success() {
        val result = DarajaResult.Success("Success").getOrThrow()

        assertNotNull(result)
        assertEquals(expected = "Success", actual = result)
    }

    /*@Test
    fun darajaResult_getOrThrow_returns_exception_on_error() {
        val result = DarajaResult.Failure(DarajaException()).getOrThrow()

        assertFailsWith<DarajaException> {
            result
        }
    }*/

    @Test
    fun darajaResult_onSuccess_returns_data_on_success() {
        val result = DarajaResult.Success(data = "Success")

        result.onSuccess {
            assertNotNull(it)
            assertEquals(expected = it, actual = "Success")
        }
    }

    @Test
    fun darajaResult_onSuccess_returns_null_on_error() {
        val result = DarajaResult.Failure(darajaException)

        result.onSuccess {
            assertNull(it) // ToDo: Unreachable code
        }
    }

    @Test
    fun darajaResult_onFailure_returns_null_on_success() {
        val result = DarajaResult.Success(data = "Success")

        result.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun darajaResult_onFailure_returns_exception_on_error() {
        val result = DarajaResult.Failure(darajaException)

        result.onFailure {
            assertNotNull(it)
            assertEquals(expected = it, actual = darajaException)
        }
    }

    @Test
    fun darajaResult_onSuccess_onFailure_on_success() {
        val result = DarajaResult.Success(data = "Success")

        result.onSuccess {
            assertNotNull(it)
            assertEquals(expected = it, actual = "Success")
        }.onFailure {
            assertNull(it)
        }
    }

    @Test
    fun darajaResult_onSuccess_onFailure_on_error() {
        val result = DarajaResult.Failure(darajaException)

        result.onSuccess {
            assertNull(it) // ToDo: Unreachable code
        }.onFailure {
            assertNotNull(it)
            assertEquals(expected = it, actual = darajaException)
        }
    }
}
