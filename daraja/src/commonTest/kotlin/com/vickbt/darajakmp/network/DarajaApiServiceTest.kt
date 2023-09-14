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

package com.vickbt.darajakmp.network

import com.vickbt.darajakmp.network.models.MpesaExpressRequest
import com.vickbt.darajakmp.network.models.MpesaExpressResponse
import com.vickbt.darajakmp.network.models.DarajaToken
import com.vickbt.darajakmp.network.models.DarajaTransactionResponse
import com.vickbt.darajakmp.network.models.DarajaTransactionRequest
import com.vickbt.darajakmp.utils.DarajaResult
import com.vickbt.darajakmp.utils.DarajaTransactionType
import io.github.reactivecircus.cache4k.Cache
import io.ktor.client.HttpClient
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlinx.coroutines.test.runTest

class DarajaApiServiceTest {

    private val mockDarajaHttpClient = MockDarajaHttpClient()

    private lateinit var mockKtorHttpClient: HttpClient
    private lateinit var mockInMemoryCache: Cache<Long, DarajaToken>

    // Subject under test
    private lateinit var darajaApiService: DarajaApiService

    private val darajaToken = DarajaToken(
        accessToken = "wWAHdtiE4GCSGv2ocfzQ0WHefwAJ", expiresIn = "3599"
    )

    private val mpesaExpressRequest = MpesaExpressRequest(
        businessShortCode = "654321",
        password = "password",
        phoneNumber = "254708374149",
        timestamp = "timestamp",
        transactionType = DarajaTransactionType.CustomerPayBillOnline.name,
        transactionDesc = "Transaction description",
        amount = "1",
        partyA = "254708374149",
        partyB = "654321",
        callBackUrl = "https://mydomain.com/path",
        accountReference = "Account reference"
    )

    private val darajaTransactionRequest = DarajaTransactionRequest(
        businessShortCode = "654321",
        password = "password",
        timestamp = "timestamp",
        checkoutRequestID = "ws_CO_07022023155508743714091304"
    )

    @BeforeTest
    fun setup() {
        mockKtorHttpClient = mockDarajaHttpClient.mockDarajaHttpClient

        mockInMemoryCache = Cache.Builder().build()

        darajaApiService = DarajaApiService(
            httpClient = mockKtorHttpClient,
            consumerKey = "consumerKey",
            consumerSecret = "consumerSecret",
            inMemoryCache = mockInMemoryCache
        )
    }

    @AfterTest
    fun tearDown() {
        mockInMemoryCache.invalidateAll()
        mockKtorHttpClient.close()
    }

    @Test
    fun fetchAccessToken_success_returns_darajaToken() = runTest {
        // when
        val actualResult = darajaApiService.fetchAccessToken()

        // then
        assertEquals(
            expected = DarajaResult.Success(darajaToken),
            actual = actualResult
        )
    }

    @Test
    fun fetchAccessToken_success_caches_darajaToken() = runTest {
        assertNull(mockInMemoryCache.get(1))

        // when
        darajaApiService.fetchAccessToken()

        // then
        val cachedToken = mockInMemoryCache.get(1)

        assertNotNull(cachedToken)
        assertEquals(expected = darajaToken, actual = cachedToken)
    }

    @Test
    fun initiateMpesaExpress_success_returns_darajaPaymentResponse() = runTest {
        assertNull(mockInMemoryCache.get(1))

        // when
        val actualResult =
            darajaApiService.initiateMpesaStk(mpesaExpressRequest = mpesaExpressRequest)
        val expectedResult = DarajaResult.Success(
            MpesaExpressResponse(
                merchantRequestID = "6093-85819535-1",
                checkoutRequestID = "ws_CO_16122022001707470708374149",
                responseCode = "0",
                responseDescription = "Success. Request accepted for processing",
                customerMessage = "Success. Request accepted for processing"
            )
        )

        // then
        assertEquals(expected = expectedResult, actual = actualResult)
        assertNotNull(mockInMemoryCache.get(1))
    }

    @Test
    fun queryTransaction_success_returns_darajaTransactionResponse() = runTest {
        // when
        val actualResult =
            darajaApiService.queryTransaction(darajaTransactionRequest = darajaTransactionRequest)
        val expectedResult = DarajaResult.Success(
            DarajaTransactionResponse(
                responseCode = "0",
                responseDescription = "The service request has been accepted successsfully",
                merchantRequestID = "15386-269505584-1",
                checkoutRequestID = "ws_CO_07022023155508743714091304",
                resultCode = "0",
                resultDescription = "The service request is processed successfully."
            )
        )

        assertEquals(expected = expectedResult, actual = actualResult)
    }
}
