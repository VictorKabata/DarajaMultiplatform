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

/**Create an instance of [Daraja] object that acts as an interface to access Daraja API functionalities
 *
 * @param consumerKey Daraja API consumer key
 * @param consumerSecret Daraja API consumer secret
 * @param passKey Daraja API passkey
 * @param environment Environment that Daraja API should use ie. Either [DarajaEnvironment.SANDBOX_ENVIRONMENT] (Sandbox Mode) or [DarajaEnvironment.PRODUCTION_ENVIRONMENT] (Production Mode)
 * */
class Daraja constructor(
    private val consumerKey: String?,
    private val consumerSecret: String?,
    private val passKey: String?,
    private val environment: DarajaEnvironment? = DarajaEnvironment.SANDBOX_ENVIRONMENT
) {

    /**Creates instance of [Daraja]
     *
     * @param [consumerKey]
     * @param [consumerSecret]
     * @param [passKey]
     * @param [environment]
     * */
    data class Builder(
        private var consumerKey: String? = null,
        private var consumerSecret: String? = null,
        private var passKey: String? = null,
        private var environment: DarajaEnvironment? = null
    ) {

        /**Provides [consumerKey] provided by Daraja API
         *
         * @param consumerKey Daraja API consumer key
         * */
        fun setConsumerKey(consumerKey: String) = apply { this.consumerKey = consumerKey }

        /**Provides [consumerSecret] provided by Daraja API
         *
         * @param consumerSecret Daraja API consumer secret
         * */
        fun setConsumerSecret(consumerSecret: String) =
            apply { this.consumerSecret = consumerSecret }

        /**Provides [passKey] provided by Daraja API
         *
         * @param passKey Daraja API pass key
         * */
        fun setPassKey(passKey: String) = apply { this.passKey = passKey }

        /**Set Daraja API environment to Sandbox/Testing mode*/
        fun isSandbox() = apply { this.environment = DarajaEnvironment.SANDBOX_ENVIRONMENT }

        /**Set Daraja API environment to Production/Live mode*/
        fun isProduction() = apply { this.environment = DarajaEnvironment.PRODUCTION_ENVIRONMENT }

        /**Create an instance of [Daraja] object with [consumerKey], [consumerSecret] and [passKey] provided*/
        fun build(): Daraja = Daraja(
            consumerKey = consumerKey,
            consumerSecret = consumerSecret,
            passKey = passKey,
            environment = environment
        )
    }

    /**Create an instance of Ktor Http Client*/
    private val darajaHttpClientFactory: HttpClient = DarajaHttpClientFactory(
        environment = environment ?: DarajaEnvironment.SANDBOX_ENVIRONMENT
    ).createDarajaHttpClient()

    /**Create instance of [DarajaApiService]*/
    private val darajaApiService: DarajaApiService = DarajaApiService(
        httpClient = darajaHttpClientFactory,
        consumerKey = consumerKey ?: "",
        consumerSecret = consumerSecret ?: ""
    )

    private val defaultDispatcher = CoroutineScope(Dispatchers.Default).coroutineContext

    /**Request access token that is used to authenticate to Daraja APIs
     *
     * @return [DarajaToken]
     * */
    fun requestAccessToken(): DarajaResult<DarajaToken> = runBlocking {
        withContext(defaultDispatcher) {
            return@withContext darajaApiService.getAccessToken()
        }
    }

    /**Initiate Mpesa Express payment of value provided in [amount] to the [businessShortCode] from the the [phoneNumber].
     * The response of the payment status will be sent to the [callbackUrl] provided.
     *
     * @param [businessShortCode] This is organizations shortcode (Paybill or Buygoods - A 5 to 7 digit account number) used to identify an organization and receive the transaction.
     * @param [amount] Money that customer pays to the [businessShortCode]
     * @param [phoneNumber] The mobile number to receive the STK pin prompt.
     * @param [transactionType] This is the transaction type that is used to identify the transaction when sending the request to M-Pesa.
     * @param [transactionDesc] This is any additional information/comment that can be sent along with the request from your system. Maximum of 13 Characters.
     * @param [callbackUrl] This is a valid secure URL that is used to receive notifications from M-Pesa API. It is the endpoint to which the results will be sent by M-Pesa API.
     * @param [accountReference] This is an alpha-numeric parameter that is defined by your system as an Identifier of the transaction for CustomerPayBillOnline transaction type.
     *
     * @return [DarajaPaymentResponse]
     * */
    fun initiateMpesaExpressPayment(
        businessShortCode: String,
        amount: Int,
        phoneNumber: String,
        transactionType: DarajaTransactionType = DarajaTransactionType.CustomerPayBillOnline,
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
