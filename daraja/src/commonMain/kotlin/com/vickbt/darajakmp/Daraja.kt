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

package com.vickbt.darajakmp

import com.vickbt.darajakmp.network.DarajaApiService
import com.vickbt.darajakmp.network.DarajaHttpClientFactory
import com.vickbt.darajakmp.network.models.DarajaToken
import com.vickbt.darajakmp.network.models.DynamicQrRequest
import com.vickbt.darajakmp.network.models.DynamicQrResponse
import com.vickbt.darajakmp.network.models.MpesaExpressRequest
import com.vickbt.darajakmp.network.models.MpesaExpressResponse
import com.vickbt.darajakmp.network.models.QueryMpesaExpressRequest
import com.vickbt.darajakmp.network.models.QueryMpesaExpressResponse
import com.vickbt.darajakmp.utils.DarajaEnvironment
import com.vickbt.darajakmp.utils.DarajaResult
import com.vickbt.darajakmp.utils.DarajaTransactionCode
import com.vickbt.darajakmp.utils.DarajaTransactionType
import com.vickbt.darajakmp.utils.getDarajaPassword
import com.vickbt.darajakmp.utils.getDarajaPhoneNumber
import com.vickbt.darajakmp.utils.getDarajaTimestamp
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlin.native.ObjCName

/**Create an instance of [Daraja] object that acts as an interface to access Daraja API functionalities
 *
 * @param consumerKey Daraja API consumer key
 * @param consumerSecret Daraja API consumer secret
 * @param passKey Daraja API passkey
 * @param environment Environment that Daraja API should use ie. Either [DarajaEnvironment.SANDBOX_ENVIRONMENT] (Sandbox Mode) or [DarajaEnvironment.PRODUCTION_ENVIRONMENT] (Production Mode)
 * */
@ObjCName(swiftName = "Daraja")
class Daraja(
    private val consumerKey: String,
    private val consumerSecret: String,
    private val passKey: String,
    private val environment: DarajaEnvironment? = DarajaEnvironment.SANDBOX_ENVIRONMENT,
) {
    private val darajaHttpClientFactory: HttpClient =
        DarajaHttpClientFactory(
            environment = environment ?: DarajaEnvironment.SANDBOX_ENVIRONMENT,
        ).createDarajaHttpClient()

    /**Creates instance of [Daraja]
     *
     * @param [consumerKey]
     * @param [consumerSecret]
     * @param [passKey]
     * @param [environment]
     * */
    data class Builder(
        @ObjCName(swiftName = "consumerKey") private var consumerKey: String,
        @ObjCName(swiftName = "consumerSecret") private var consumerSecret: String,
        @ObjCName(swiftName = "passKey") private var passKey: String,
        @ObjCName(swiftName = "darajaEnvironment") private var environment: DarajaEnvironment = DarajaEnvironment.SANDBOX_ENVIRONMENT,
    ) {
        /**Provides [consumerKey] provided by Daraja API
         *
         * @param consumerKey Daraja API consumer key
         * */
        @ObjCName(swiftName = "withConsumerKey")
        fun setConsumerKey(consumerKey: String) = apply { this.consumerKey = consumerKey }

        /**Provides [consumerSecret] provided by Daraja API
         *
         * @param consumerSecret Daraja API consumer secret
         * */
        @ObjCName(swiftName = "withConsumerSecret")
        fun setConsumerSecret(consumerSecret: String) = apply { this.consumerSecret = consumerSecret }

        /**Provides [passKey] provided by Daraja API
         *
         * @param passKey Daraja API pass key
         * */
        @ObjCName(swiftName = "withPassKey")
        fun setPassKey(passKey: String) = apply { this.passKey = passKey }

        /**Set Daraja API environment to Sandbox/Testing mode*/
        fun setSandboxEnvironment() = apply { this.environment = DarajaEnvironment.SANDBOX_ENVIRONMENT }

        /**Set Daraja API environment to Production/Live mode*/
        fun setProductionEnvironment() = apply { this.environment = DarajaEnvironment.PRODUCTION_ENVIRONMENT }

        /**Create an instance of [Daraja] object with [consumerKey], [consumerSecret] and [passKey] provided*/
        @ObjCName(swiftName = "init")
        fun build(): Daraja =
            Daraja(
                consumerKey = consumerKey,
                consumerSecret = consumerSecret,
                passKey = passKey,
                environment = environment,
            )
    }

    /**Create instance of [DarajaApiService]*/
    private val darajaApiService: DarajaApiService =
        DarajaApiService(
            httpClient = darajaHttpClientFactory,
            consumerKey =
            consumerKey,
            consumerSecret =
            consumerSecret,
        )

    /**Request access token that is used to authenticate to Daraja APIs
     *
     * @return [DarajaToken]
     * */
    @ObjCName(swiftName = "authorization")
    fun authorization(): DarajaResult<DarajaToken> =
        runBlocking(Dispatchers.IO) {
            darajaApiService.fetchAccessToken()
        }

    /**Initiate Mpesa Express payment of value provided in [amount] to the [businessShortCode] from the the [phoneNumber].
     * The response of the payment status will be sent to the [callbackUrl] provided.
     *
     * @param [businessShortCode] This is organizations shortcode (Paybill or Buy Goods - A 5 to 7 digit account number) used to identify an organization and receive the transaction.
     * @param [amount] Money that customer pays to the [businessShortCode]
     * @param [phoneNumber] The mobile number to receive the STK pin prompt.
     * @param [transactionType] This is the transaction type that is used to identify the transaction when sending the request to M-Pesa.
     * @param [transactionDesc] This is any additional information/comment that can be sent along with the request from your system. Maximum of 13 Characters.
     * @param [callbackUrl] This is a valid secure URL that is used to receive notifications from M-Pesa API. It is the endpoint to which the results will be sent by M-Pesa API.
     * @param [accountReference] This is an alpha-numeric parameter that is defined by your system as an Identifier of the transaction for CustomerPayBillOnline transaction type.
     *
     * @return [MpesaExpressResponse]
     * */
    @ObjCName(swiftName = "mpesaExpress")
    fun mpesaExpress(
        businessShortCode: String,
        amount: Int,
        phoneNumber: String,
        transactionType: DarajaTransactionType = DarajaTransactionType.CustomerPayBillOnline,
        transactionDesc: String,
        callbackUrl: String,
        accountReference: String? = null,
    ): DarajaResult<MpesaExpressResponse> =
        runBlocking(Dispatchers.IO) {
            val timestamp = Clock.System.now().getDarajaTimestamp()

            val darajaPassword =
                getDarajaPassword(
                    shortCode = businessShortCode,
                    passkey = passKey,
                    timestamp = timestamp,
                )

            val mpesaExpressRequest =
                MpesaExpressRequest(
                    businessShortCode = businessShortCode,
                    password = darajaPassword,
                    timestamp = timestamp,
                    transactionDesc = transactionDesc,
                    amount = amount.toString(),
                    transactionType = transactionType.name,
                    phoneNumber = phoneNumber.getDarajaPhoneNumber(),
                    callBackUrl = callbackUrl,
                    accountReference = accountReference ?: businessShortCode,
                    partyA = phoneNumber,
                    partyB = businessShortCode,
                )

            darajaApiService.initiateMpesaExpress(mpesaExpressRequest = mpesaExpressRequest)
        }

    /**
     * @param [businessShortCode] - This is the organization's shortcode (Paybill or Buy Goods) used to identify an organization and receive the transaction.
     * @param [timestamp] - This is the timestamp of the transaction, normally in the format of Year+Month+Date+Hour+Minute+Second(YYYYMMDDHHmmss)
     * @param [checkoutRequestID] - This is a global unique identifier of the processed checkout transaction request.
     * */
    @ObjCName(swiftName = "mpesaExpressQuery")
    fun mpesaExpressQuery(
        businessShortCode: String,
        timestamp: String,
        checkoutRequestID: String,
    ): DarajaResult<QueryMpesaExpressResponse> =
        runBlocking(Dispatchers.IO) {
            val darajaPassword =
                getDarajaPassword(
                    shortCode = businessShortCode,
                    passkey = passKey,
                    timestamp = timestamp,
                )

            val queryMpesaExpressRequest =
                QueryMpesaExpressRequest(
                    businessShortCode = businessShortCode,
                    password = darajaPassword,
                    timestamp = timestamp,
                    checkoutRequestID = checkoutRequestID,
                )

            darajaApiService.queryMpesaExpress(queryMpesaExpressRequest = queryMpesaExpressRequest)
        }

    /**Generate a dynamic qr code to initiate payment
     *
     * @param [merchantName] Name of the company/M-Pesa merchant name
     * @param referenceNumber Transaction reference
     * @param amount The total amount for the sale/transaction.
     * @param transactionCode Transaction Type. The supported types are:
     * BG: Pay Merchant (Buy Goods).
     *
     * WA: Withdraw Cash at Agent Till.
     *
     * PB: Paybill or Business number.
     *
     * SM: Send Money(Mobile number)
     *
     * SB: Sent to Business. Business number CPI in MSISDN format.
     * @param cpi Credit Party Identifier. Can be a mobile number, business number, agent till, paybill or business number, or merchant buy goods.
     * @param size Size of the QR code image in pixels. QR code image will always be a square image.
     *
     * @return [DynamicQrResponse]
     * */
    fun generateDynamicQr(
        merchantName: String,
        referenceNumber: String,
        amount: Int,
        transactionCode: DarajaTransactionCode,
        cpi: String,
        size: Int,
    ): DarajaResult<DynamicQrResponse> =
        runBlocking(Dispatchers.IO) {
            val dynamicQrRequest =
                DynamicQrRequest(
                    merchantName = merchantName,
                    referenceNumber = referenceNumber,
                    amount = amount,
                    transactionCode = transactionCode.name,
                    cpi = cpi,
                    size = size.toString(),
                )

            darajaApiService.generateDynamicQr(dynamicQrRequest = dynamicQrRequest)
        }
}
