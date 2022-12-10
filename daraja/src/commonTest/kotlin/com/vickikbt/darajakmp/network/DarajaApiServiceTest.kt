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

package com.vickikbt.darajakmp.network

class DarajaApiServiceTest {

    /*private lateinit var mockHttpClient: HttpClient
    private lateinit var darajaApiService: DarajaApiService

    private val authToken: DarajaToken =
        DarajaToken(accessToken = "access_token", expiresIn = "expires_in")

    @BeforeTest
    fun setup() {
        mockHttpClient = HttpClient(MockEngine.create())

        darajaApiService =
            DarajaApiService(
                httpClient = mockHttpClient,
                consumerKey = "consumer_key",
                consumerSecret = "consumer_secret"
            )
    }

    @AfterTest
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun getAuthToken_returns_result_success_on_success() = runTest {
        // given
        coEvery { darajaApiService.getAuthToken() } returns Result.success(authToken)

        // when
        val result = darajaApiService.getAuthToken()

        // then
        assertTrue(actual = result.isSuccess)
        assertEquals(expected = Result.success(authToken), actual = result)
    }

    @Test
    fun getAuthToken_returns_result_error_on_error() = runTest {
        // given
        val exception = Exception()
        coEvery { darajaApiService.getAuthToken() } throws exception

        // when
        val result = darajaApiService.getAuthToken()

        // then
        assertTrue(actual = result.isFailure)
        assertEquals(expected = Result.failure(exception), actual = result)
    }*/
}
