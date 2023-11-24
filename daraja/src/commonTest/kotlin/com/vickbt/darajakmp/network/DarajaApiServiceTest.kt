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

import com.vickbt.darajakmp.network.models.AccessToken200JSON
import com.vickbt.darajakmp.network.models.AccessToken400JSON
import com.vickbt.darajakmp.network.models.C2BRegistrationRequest
import com.vickbt.darajakmp.network.models.C2BRequest
import com.vickbt.darajakmp.network.models.C2BResponse
import com.vickbt.darajakmp.network.models.C2BResponse200JSON
import com.vickbt.darajakmp.network.models.DarajaException
import com.vickbt.darajakmp.network.models.DarajaToken
import com.vickbt.darajakmp.network.models.DarajaTransactionRequest
import com.vickbt.darajakmp.network.models.DarajaTransactionResponse
import com.vickbt.darajakmp.network.models.MpesaExpress200JSON
import com.vickbt.darajakmp.network.models.MpesaExpressRequest
import com.vickbt.darajakmp.network.models.MpesaExpressResponse
import com.vickbt.darajakmp.network.models.QueryTransaction200JSON
import com.vickbt.darajakmp.utils.C2BResponseType
import com.vickbt.darajakmp.utils.DarajaResult
import com.vickbt.darajakmp.utils.DarajaTransactionType
import io.github.reactivecircus.cache4k.Cache
import io.github.reactivecircus.cache4k.FakeTimeSource
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

class DarajaApiServiceTest {

    private var fakeTimeSource = FakeTimeSource()
    private val mockDarajaHttpClient = MockDarajaHttpClient()

    private lateinit var mockKtorHttpClient: HttpClient
    private lateinit var mockInMemoryCache: Cache<Long, DarajaToken>

    // Subject under test
    private lateinit var darajaApiService: DarajaApiService

    private val darajaTokenResponse = Json.decodeFromString<DarajaToken>(AccessToken200JSON)
    private val mpesaExpressResponse =
        Json.decodeFromString<MpesaExpressResponse>(MpesaExpress200JSON)
    private val darajaTransactionResponse =
        Json.decodeFromString<DarajaTransactionResponse>(QueryTransaction200JSON)
    private val c2bResponse = Json.decodeFromString<C2BResponse>(C2BResponse200JSON)

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

    private val c2bRegistrationRequest = C2BRegistrationRequest(
        confirmationURL = "https://mydomain.com/confirmation",
        validationURL = "https://mydomain.com/validation",
        responseType = C2BResponseType.COMPLETED.name,
        shortCode = "601426"
    )

    private val c2bRequest = C2BRequest(
        amount = "1",
        billReferenceNumber = "invoice001",
        commandID = "",
        phoneNumber = "254717091452",
        shortCode = "601426"
    )

    @BeforeTest
    fun setup() {
        mockKtorHttpClient = mockDarajaHttpClient.mockDarajaHttpClient

        mockInMemoryCache = Cache.Builder<Long, DarajaToken>()
            .timeSource(timeSource = fakeTimeSource)
            .expireAfterWrite(3600.seconds)
            .build()

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
    fun `fetchAccessToken returns darajaToken on success`() = runTest {
        val actualResult = darajaApiService.fetchAccessToken()

        assertEquals(
            expected = DarajaResult.Success(darajaTokenResponse),
            actual = actualResult
        )
    }

    @Test
    fun `fetchAccessToken returns exception on 400 error`() = runTest {
        mockDarajaHttpClient.throwError(
            httpStatus = HttpStatusCode.BadRequest,
            response = AccessToken400JSON
        )

        val actualResult = darajaApiService.fetchAccessToken()

        assertEquals(
            expected = DarajaResult.Failure(
                exception = DarajaException(
                    requestId = "43301-58413611-1",
                    errorCode = "400.008.01",
                    errorMessage = "Invalid Authentication passed"
                )
            ),
            actual = actualResult
        )
    }

    @Test
    fun `fetchAccessToken caches darajaToken on success`() = runTest {
        assertNull(mockInMemoryCache.get(1))

        darajaApiService.fetchAccessToken()

        val cachedToken = mockInMemoryCache.get(1)

        assertNotNull(cachedToken)
        assertEquals(expected = darajaTokenResponse, actual = cachedToken)
    }

    @Test
    fun `fetchAccessToken cache expires after 3600 seconds`() = runTest {
        assertNull(mockInMemoryCache.get(1))

        darajaApiService.fetchAccessToken()

        // just before expiry
        fakeTimeSource += 3600.seconds - 1.nanoseconds
        assertNotNull(mockInMemoryCache.get(1))
        assertEquals(expected = darajaTokenResponse, mockInMemoryCache.get(1))

        // just after expiry
        fakeTimeSource += 1.nanoseconds
        assertNull(mockInMemoryCache.get(1))
    }

    @Test
    fun `initiateMpesaExpress returns darajaPaymentResponse on success`() = runTest {
        assertNull(mockInMemoryCache.get(1))

        val actualResult =
            darajaApiService.initiateMpesaExpress(mpesaExpressRequest = mpesaExpressRequest)
        val expectedResult = DarajaResult.Success(data = mpesaExpressResponse)

        assertEquals(expected = expectedResult, actual = actualResult)
        assertNotNull(mockInMemoryCache.get(1))
        assertEquals(expected = darajaTokenResponse, actual = mockInMemoryCache.get(1))
    }

    @Test
    fun `queryTransaction returns darajaTransactionResponse on success`() = runTest {
        val actualResult =
            darajaApiService.queryTransaction(darajaTransactionRequest = darajaTransactionRequest)

        val expectedResult = DarajaResult.Success(data = darajaTransactionResponse)

        assertEquals(expected = expectedResult, actual = actualResult)
    }

    @Test
    fun `c2B registration returns c2bResponse on success`() = runTest {
        val actualResult =
            darajaApiService.c2bRegistration(c2bRegistrationRequest = c2bRegistrationRequest)

        val expectedResult = DarajaResult.Success(data = c2bResponse)

        assertEquals(expected = expectedResult, actual = actualResult)
    }

    @Test
    fun `c2B returns c2bResponse on success`() = runTest {
        val actualResult = darajaApiService.c2b(c2bRequest = c2bRequest)

        val expectedResult = DarajaResult.Success(data = c2bResponse)

        assertEquals(expected = expectedResult, actual = actualResult)
    }
}
