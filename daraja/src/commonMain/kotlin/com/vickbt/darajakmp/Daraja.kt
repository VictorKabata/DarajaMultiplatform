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
import com.vickbt.darajakmp.network.models.AccountBalanceRequest
import com.vickbt.darajakmp.network.models.AccountBalanceResponse
import com.vickbt.darajakmp.network.models.C2BRegistrationRequest
import com.vickbt.darajakmp.network.models.C2BRegistrationResponse
import com.vickbt.darajakmp.network.models.C2BRequest
import com.vickbt.darajakmp.network.models.C2BResponse
import com.vickbt.darajakmp.network.models.DarajaException
import com.vickbt.darajakmp.network.models.DarajaToken
import com.vickbt.darajakmp.network.models.DarajaTransactionRequest
import com.vickbt.darajakmp.network.models.DarajaTransactionResponse
import com.vickbt.darajakmp.network.models.DynamicQrRequest
import com.vickbt.darajakmp.network.models.DynamicQrResponse
import com.vickbt.darajakmp.network.models.MpesaExpressRequest
import com.vickbt.darajakmp.network.models.MpesaExpressResponse
import com.vickbt.darajakmp.network.models.QueryMpesaExpressRequest
import com.vickbt.darajakmp.network.models.QueryMpesaExpressResponse
import com.vickbt.darajakmp.utils.C2BResponseType
import com.vickbt.darajakmp.utils.DarajaEnvironment
import com.vickbt.darajakmp.utils.DarajaIdentifierType
import com.vickbt.darajakmp.utils.DarajaResult
import com.vickbt.darajakmp.utils.DarajaTransactionCode
import com.vickbt.darajakmp.utils.DarajaTransactionType
import com.vickbt.darajakmp.utils.capitalize
import com.vickbt.darajakmp.utils.getDarajaPassword
import com.vickbt.darajakmp.utils.getDarajaPhoneNumber
import com.vickbt.darajakmp.utils.getDarajaTimestamp
import io.ktor.client.HttpClient
import io.ktor.util.encodeBase64
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
    private val consumerKey: String?,
    private val consumerSecret: String?,
    private val passKey: String?,
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
        @ObjCName(swiftName = "consumerKey") private var consumerKey: String? = null,
        @ObjCName(swiftName = "consumerSecret") private var consumerSecret: String? = null,
        @ObjCName(swiftName = "passKey") private var passKey: String? = null,
        @ObjCName(swiftName = "darajaEnvironment") private var environment: DarajaEnvironment? = null,
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
        fun setConsumerSecret(consumerSecret: String) =
            apply { this.consumerSecret = consumerSecret }

        /**Provides [passKey] provided by Daraja API
         *
         * @param passKey Daraja API pass key
         * */
        @ObjCName(swiftName = "withPassKey")
        fun setPassKey(passKey: String) = apply { this.passKey = passKey }

        /**Set Daraja API environment to Sandbox/Testing mode*/
        fun isSandbox() = apply { this.environment = DarajaEnvironment.SANDBOX_ENVIRONMENT }

        /**Set Daraja API environment to Production/Live mode*/
        fun isProduction() = apply { this.environment = DarajaEnvironment.PRODUCTION_ENVIRONMENT }

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
            consumerKey
                ?: throw DarajaException(errorMessage = "Consumer key is null"),
            consumerSecret =
            consumerSecret
                ?: throw DarajaException(errorMessage = "Consumer secret is null"),
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
                    passkey = passKey ?: throw DarajaException(errorMessage = "Pass key is null"),
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
                    passkey = passKey ?: "",
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

    /**Request the status of an Mpesa payment transaction
     *
     * @param [businessShortCode] This is organizations shortcode (Paybill or Buy Goods - A 5 to 7 digit account number) used to identify an organization and receive the transaction.
     * @param [checkoutRequestID] This is a global unique identifier of the processed checkout transaction request.
     *
     * @return [DarajaTransactionResponse]
     * */
    @ObjCName(swiftName = "transactionStatus")
    internal fun transactionStatus(
        businessShortCode: String,
        checkoutRequestID: String,
    ): DarajaResult<DarajaTransactionResponse> =
        runBlocking(Dispatchers.IO) {
            val timestamp = Clock.System.now().getDarajaTimestamp()
            val darajaPassword =
                getDarajaPassword(
                    shortCode = businessShortCode,
                    passkey = passKey ?: throw DarajaException(errorMessage = "Pass key is null"),
                    timestamp = timestamp,
                )

            val darajaTransactionRequest =
                DarajaTransactionRequest(
                    businessShortCode = businessShortCode,
                    password = darajaPassword,
                    timestamp = timestamp,
                    checkoutRequestID = checkoutRequestID,
                )

            darajaApiService.queryTransaction(darajaTransactionRequest)
        }

    /**Transact between a phone number registered on M-Pesa to an M-Pesa shortcode
     *
     * @param [businessShortCode] A unique number is tagged to an M-PESA pay bill/till number of the organization.
     * @param [confirmationURL] This is the URL that receives the confirmation request from API upon payment completion.
     * @param [validationURL] This is the URL that receives the validation request from the API upon payment submission. The validation URL is only called if the external validation on the registered shortcode is enabled. (By default External Validation is disabled).
     * @param [responseType] This parameter specifies what is to happen if for any reason the validation URL is not reachable. Note that, this is the default action value that determines what M-PESA will do in the scenario that your endpoint is unreachable or is unable to respond on time. Only two values are allowed: Completed or Cancelled. Completed means M-PESA will automatically complete your transaction, whereas Cancelled means M-PESA will automatically cancel the transaction, in the event M-PESA is unable to reach your Validation URL.
     *
     * @return [C2BRegistrationResponse]
     * */
    fun c2bRegistration(
        businessShortCode: String,
        confirmationURL: String,
        validationURL: String,
        responseType: C2BResponseType = C2BResponseType.COMPLETED,
    ): DarajaResult<C2BRegistrationResponse> =
        runBlocking(Dispatchers.IO) {
            val c2BRegistrationRequest =
                C2BRegistrationRequest(
                    confirmationURL = confirmationURL,
                    validationURL = validationURL,
                    responseType = if (validationURL.isEmpty()) C2BResponseType.COMPLETED.name.capitalize()
                    else responseType.name.capitalize(),
                    shortCode = businessShortCode,
                )

            darajaApiService.c2bRegistration(c2bRegistrationRequest = c2BRegistrationRequest)
        }

    fun c2b(
        amount: Int,
        billReferenceNumber: String? = null,
        transactionType: DarajaTransactionType,
        phoneNumber: String,
        businessShortCode: String,
    ): DarajaResult<C2BResponse> =
        runBlocking(Dispatchers.IO) {
            val c2bRequest =
                C2BRequest(
                    amount = amount,
                    billReferenceNumber = if (transactionType.name == DarajaTransactionType.CustomerPayBillOnline.name) {
                        billReferenceNumber
                    } else {
                        null
                    },
                    commandID = transactionType.name,
                    phoneNumber = phoneNumber.getDarajaPhoneNumber().toLong(),
                    shortCode = businessShortCode,
                )

            darajaApiService.c2b(c2bRequest = c2bRequest)
        }

    /**Request the account balance of a short code. This can be used for both B2C, buy goods and pay bill accounts.
     *
     * @param [initiator] This is the credential/username used to authenticate the transaction request
     * @param [initiatorPassword] This is the credential/password used to authenticate the account balance request
     * @param [commandId] A unique command is passed to the M-PESA system. Max length is 64.
     * @param [partyA] The shortcode of the organization querying for the account balance.
     * @param [identifierType] Type of organization querying for the account balance.
     * @param [remarks] Comments that are sent along with the transaction
     * @param [queueTimeOutURL] The end-point that receives a timeout message.
     * @param [resultURL] It indicates the destination URL which Daraja should send the result message to.
     *
     * @return [AccountBalanceResponse]
     * */
    internal fun accountBalance(
        initiator: String,
        initiatorPassword: String,
        commandId: String = "AccountBalance",
        partyA: Int,
        identifierType: DarajaIdentifierType,
        remarks: String = "Account balance request",
        queueTimeOutURL: String,
        resultURL: String,
    ): DarajaResult<AccountBalanceResponse> =
        runBlocking(Dispatchers.IO) {
            val key = initiator + initiatorPassword
            val securityCredential = key.encodeBase64()

            val accountBalanceRequest =
                AccountBalanceRequest(
                    initiator = initiator,
                    securityCredential = securityCredential,
                    commandId = commandId,
                    partyA = partyA,
                    identifierType = if (identifierType == DarajaIdentifierType.TILL_NUMBER) 2 else 4,
                    remarks = remarks,
                    queueTimeOutURL = queueTimeOutURL,
                    resultURL = resultURL,
                )

            darajaApiService.accountBalance(accountBalanceRequest = accountBalanceRequest)
        }
}
