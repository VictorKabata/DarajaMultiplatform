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

package com.vickikbt.darajakmp

import com.vickikbt.darajakmp.network.DarajaApiService
import com.vickikbt.darajakmp.network.DarajaHttpClientFactory
import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaEnvironment
import com.vickikbt.darajakmp.utils.DarajaResult
import com.vickikbt.darajakmp.utils.DarajaTransactionType
import com.vickikbt.darajakmp.utils.getDarajaPassword
import com.vickikbt.darajakmp.utils.getDarajaPhoneNumber
import com.vickikbt.darajakmp.utils.getDarajaTimestamp
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock

class Daraja constructor(
    private val consumerKey: String?,
    private val consumerSecret: String?,
    private val passKey: String?,
    private val environment: DarajaEnvironment? = DarajaEnvironment.SANDBOX_ENVIRONMENT
) {

    data class Builder(
        private var consumerKey: String? = null,
        private var consumerSecret: String? = null,
        private var passKey: String? = null,
        private var environment: DarajaEnvironment? = null
    ) {

        fun setConsumerKey(consumerKey: String) = apply { this.consumerKey = consumerKey }

        fun setConsumerSecret(consumerSecret: String) =
            apply { this.consumerSecret = consumerSecret }

        fun setPassKey(passKey: String) = apply { this.passKey = passKey }

        fun isSandbox() = apply { this.environment = DarajaEnvironment.SANDBOX_ENVIRONMENT }

        fun isProduction() = apply { this.environment = DarajaEnvironment.PRODUCTION_ENVIRONMENT }

        fun build(): Daraja = Daraja(
            consumerKey = consumerKey,
            consumerSecret = consumerSecret,
            passKey = passKey,
            environment = environment
        )
    }

    private val darajaHttpClientFactory: HttpClient = DarajaHttpClientFactory(
        environment = environment ?: DarajaEnvironment.SANDBOX_ENVIRONMENT
    ).createDarajaHttpClient()

    private val darajaApiService: DarajaApiService = DarajaApiService(
        httpClient = darajaHttpClientFactory,
        consumerKey = consumerKey ?: "",
        consumerSecret = consumerSecret ?: ""
    )

    private val defaultDispatcher = CoroutineScope(Dispatchers.Default).coroutineContext

    fun requestAuthToken(): DarajaResult<DarajaToken> = runBlocking {
        withContext(defaultDispatcher) {
            return@withContext darajaApiService.getAuthToken()
        }
    }

    fun initiateDarajaStk(
        businessShortCode: String,
        amount: Int,
        phoneNumber: String,
        transactionType: DarajaTransactionType,
        transactionDesc: String,
        callbackUrl: String,
        accountReference: String? = null
    ): DarajaResult<DarajaPaymentResponse> = runBlocking {
        val timestamp = Clock.System.now().getDarajaTimestamp()

        val darajaPassword = getDarajaPassword(
            shortCode = businessShortCode,
            passkey = passKey ?: "",
            timestamp = timestamp
        )

        val darajaPaymentRequest = DarajaPaymentRequest(
            businessShortCode = businessShortCode,
            password = darajaPassword,
            timestamp = timestamp,
            transactionDesc = transactionDesc,
            amount = amount.toString(),
            transactionType = transactionType.name,
            phoneNumber = phoneNumber.getDarajaPhoneNumber() ?: phoneNumber,
            callBackUrl = callbackUrl, // ToDo: Figure out how callback urls work
            accountReference = accountReference ?: businessShortCode,
            partyA = phoneNumber,
            partyB = businessShortCode
        )

        withContext(defaultDispatcher) {
            return@withContext darajaApiService.initiateMpesaStk(darajaPaymentRequest = darajaPaymentRequest)
        }
    }
}
