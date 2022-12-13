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

package com.vickikbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Request body sent to Daraja API to request Mpesa Express payment
 *
 * @param [businessShortCode] This is organizations shortcode (Paybill or Buygoods - A 5 to 7 digit account number) used to identify an organization and receive the transaction.
 * @param [password] This is the password used for encrypting the request sent: A base64 encoded string. (The base64 string is a combination of [Shortcode]+[Passkey]+[Timestamp]).
 * @param [phoneNumber] The mobile number to receive the STK pin prompt. This is the same as [partyA]
 * @param [timestamp] This is the Timestamp of the transaction in the format of YEAR+MONTH+DATE+HOUR+MINUTE+SECOND (YYYYMMDDHHMMSS)
 * @param [transactionType] This is the transaction type that is used to identify the transaction when sending the request to M-Pesa.
 * @param [amount] Money that customer pays to the [businessShortCode].
 * @param [partyA] The phone number sending money.
 * @param [partyB] The organization receiving the funds. This is the same as [businessShortCode]
 * @param [callBackUrl] This is a valid secure URL that is used to receive notifications from M-Pesa API. It is the endpoint to which the results will be sent by M-Pesa API.
 * @param [accountReference] This is an alpha-numeric parameter that is defined by your system as an Identifier of the transaction for CustomerPayBillOnline transaction type
 * @param [transactionDesc] This is any additional information/comment that can be sent along with the request from your system. Maximum of 13 Characters.
 * */
data class DarajaPaymentRequest(

    @SerialName("BusinessShortCode")
    val businessShortCode: String,

    @SerialName("Password")
    val password: String,

    @SerialName("PhoneNumber")
    val phoneNumber: String,

    @SerialName("Timestamp")
    val timestamp: String,

    @SerialName("TransactionType")
    val transactionType: String,

    @SerialName("Amount")
    val amount: String,

    @SerialName("PartyA") //🥳
    val partyA: String,

    @SerialName("PartyB")
    val partyB: String,

    @SerialName("CallBackURL")
    val callBackUrl: String,

    @SerialName("AccountReference")
    val accountReference: String,

    @SerialName("TransactionDesc")
    val transactionDesc: String,
)
