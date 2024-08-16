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

import assertk.all
import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isSameInstanceAs
import assertk.assertions.prop
import com.vickbt.darajakmp.network.models.DarajaException
import com.vickbt.darajakmp.utils.DarajaResult.Success
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNull

class DarajaResultTest {
    private val darajaException =
        DarajaException(
            requestId = "43301-58413611-1",
            errorCode = "400.008.01",
            errorMessage = "Invalid Authentication passed",
        )

    @Test
    fun `darajaResult getOrNull returns data on success`() = runTest {
        val result = Success(data = "Success").getOrNull()

        assertThat(result).isEqualTo("Success")
    }

    @Test
    fun `darajaResult getOrNull returns null on error`() = runTest {
        val result = DarajaResult.Failure(exception = darajaException).getOrNull()

        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `darajaResult throwOnFailure returns exception on error`() = runTest {
        val result = DarajaResult.Failure(exception = darajaException).throwOnFailure()

        assertThat(result).all {
            hasMessage(darajaException.errorMessage)
            isSameInstanceAs(darajaException)
        }
    }

    @Test
    fun `darajaResult getOrThrow returns data on success`() = runTest {
        val result = Success("Success").getOrThrow()

        assertThat(result).isEqualTo("Success")
    }

    @Test
    fun `darajaResult onSuccess returns data on success`() = runTest {
        val result = Success(data = "Success")

        result.onSuccess {
            assertThat(it).isEqualTo("Success")
        }.onFailure {
            assertThat(it).all {
                prop(DarajaException::errorMessage).isNotNull()
                isSameInstanceAs(DarajaException::errorMessage)
            }
        }
    }

    @Test
    fun `darajaResult onSuccess returns null on error`() = runTest {
        val result = DarajaResult.Failure(darajaException)

        result.onSuccess {
            assertNull(it)
        }.onFailure {
            assertThat(it.errorMessage).isEqualTo("Invalid Authentication passed")
        }
    }

    @Test
    fun `darajaResult onFailure returns null on success`() = runTest {
        val result = Success(data = "Success")

        result.onFailure {
            assertThat(it.errorMessage).isEqualTo("Invalid Authentication passed")
        }
    }

    @Test
    fun `darajaResult onFailure returns exception on error`() = runTest {
        val result = DarajaResult.Failure(darajaException)

        result.onFailure {
            assertThat(it).all {
                prop(DarajaException::errorMessage).isEqualTo("Invalid Authentication passed")
            }
        }
    }

    @Test
    fun `darajaResult onSuccess does not trigger onFailure on success`() = runTest {
        val result = Success(data = "Success")

        result.onSuccess {
            assertThat(it).isEqualTo("Success")
        }.onFailure {
            assertThat(it).isNull()
        }
    }

    @Test
    fun `darajaResult onSuccess returns onFailure on error`() = runTest {
        val result = DarajaResult.Failure(darajaException)

        result.onFailure {
            assertThat(it).all {
                prop(DarajaException::errorMessage).isEqualTo("Invalid Authentication passed")
            }
        }
    }
}
