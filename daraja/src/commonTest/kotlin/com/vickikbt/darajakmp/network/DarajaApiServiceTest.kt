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

import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaResult
import com.vickikbt.darajakmp.utils.DarajaTransactionType
import io.ktor.client.HttpClient
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class DarajaApiServiceTest {

    private lateinit var mockKtorHttpClient: HttpClient

    // Subject under test
    private lateinit var darajaApiService: DarajaApiService

    private val darajaToken = DarajaToken(
        accessToken = "wWAHdtiE4GCSGv2ocfzQ0WHefwAJ",
        expiresIn = "3599"
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
        accountReference = "Account reference",
    )

    @BeforeTest
    fun setup() {
        mockKtorHttpClient = mockDarajaHttpClient

        darajaApiService = DarajaApiService(
            httpClient = mockKtorHttpClient,
            consumerKey = "",
            consumerSecret = ""
        )
    }

    @Test
    fun fetchAccessToken_SuccessResponse() = runTest {
        // when
        val actualResult = darajaApiService.fetchAccessToken()
        val expectedResult = DarajaResult.Success(darajaToken)

        // then
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun initiateMpesaExpress_SuccessResponse() = runTest {
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
        assertEquals(expectedResult, actualResult)
    }
}
