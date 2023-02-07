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

import com.vickbt.darajakmp.network.models.DarajaException
import com.vickbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickbt.darajakmp.network.models.DarajaToken
import com.vickbt.darajakmp.network.models.DarajaTransactionResponse
import com.vickbt.darajakmp.network.models.InvalidAccessTokenJSON
import com.vickbt.darajakmp.network.models.QueryDarajaTransactionRequest
import com.vickbt.darajakmp.utils.DarajaResult
import com.vickbt.darajakmp.utils.DarajaTransactionType
import io.github.reactivecircus.cache4k.Cache
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class DarajaApiServiceTest {

    private val mockDarajaHttpClient = MockDarajaHttpClient()

    private lateinit var mockKtorHttpClient: HttpClient
    private lateinit var mockInMemoryCache: Cache<Long, DarajaToken>

    // Subject under test
    private lateinit var darajaApiService: DarajaApiService

    private val darajaToken = DarajaToken(
        accessToken = "wWAHdtiE4GCSGv2ocfzQ0WHefwAJ", expiresIn = "3599"
    )

    private val darajaPaymentRequest = DarajaPaymentRequest(
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

    private val queryDarajaTransactionRequest = QueryDarajaTransactionRequest(
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
            consumerSecret = "consumerSecret"
        )
    }

    @AfterTest
    fun tearDown() {
        mockInMemoryCache.invalidateAll()
    }

    @Test
    fun fetchAccessToken_success() = runTest {
        // when
        val actualResult = darajaApiService.fetchAccessToken()

        // then
        assertEquals(expected = DarajaResult.Success(darajaToken), actual = actualResult)
    }

    @Test
    fun initiateMpesaExpress_success() = runTest {
        assertNull(mockInMemoryCache.get(1))

        // when
        val actualResult =
            darajaApiService.initiateMpesaStk(darajaPaymentRequest = darajaPaymentRequest)
        val expectedResult = DarajaResult.Success(
            DarajaPaymentResponse(
                merchantRequestID = "6093-85819535-1",
                checkoutRequestID = "ws_CO_16122022001707470708374149",
                responseCode = "0",
                responseDescription = "Success. Request accepted for processing",
                customerMessage = "Success. Request accepted for processing"
            )
        )

        // then
        assertEquals(expected = expectedResult, actual = actualResult)
    }

    @Test
    fun initiateMpesaExpress_404_failure() = runTest {
        // given
        mockDarajaHttpClient.throwError(
            httpStatus = HttpStatusCode.Unauthorized,
            response = InvalidAccessTokenJSON
        )

        // then - when
        assertFailsWith<DarajaException> {
            darajaApiService.queryTransaction(queryDarajaTransactionRequest = queryDarajaTransactionRequest)
        }
    }

    @Test
    fun queryTransaction_success() = runTest {
        // when
        val actualResult =
            darajaApiService.queryTransaction(queryDarajaTransactionRequest = queryDarajaTransactionRequest)
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
