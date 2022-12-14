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
 * Request body sent to Daraja API to request Mpesa Express payment.
 * */
data class DarajaPaymentRequest(

    @SerialName("BusinessShortCode")
    /**This is organizations shortcode (Paybill or Buygoods - A 5 to 7 digit account number) used to identify an organization and receive the transaction.*/
    val businessShortCode: String,

    @SerialName("Password")
    /**This is the password used for encrypting the request sent: A base64 encoded string. (The base64 string is a combination of [Shortcode]+[Passkey]+[Timestamp]).*/
    val password: String,

    @SerialName("PhoneNumber")
    /**The mobile number to receive the STK pin prompt. This is the same as [partyA].*/
    val phoneNumber: String,

    @SerialName("Timestamp")
    /**This is the Timestamp of the transaction in the format of YEAR+MONTH+DATE+HOUR+MINUTE+SECOND (YYYYMMDDHHMMSS).*/
    val timestamp: String,

    @SerialName("TransactionType")
    /**This is the transaction type that is used to identify the transaction when sending the request to M-Pesa.*/
    val transactionType: String,

    @SerialName("Amount")
    /**Money that customer pays to the [businessShortCode].*/
    val amount: String,

    @SerialName("PartyA") //🥳
    /**The phone number sending money.*/
    val partyA: String,

    @SerialName("PartyB")
    /**The organization receiving the funds. This is the same as [businessShortCode].*/
    val partyB: String,

    @SerialName("CallBackURL")
    /**This is a valid secure URL that is used to receive notifications from M-Pesa API. It is the endpoint to which the results will be sent by M-Pesa API.*/
    val callBackUrl: String,

    @SerialName("AccountReference")
    /**This is an alpha-numeric parameter that is defined by your system as an Identifier of the transaction for CustomerPayBillOnline transaction type.*/
    val accountReference: String,

    @SerialName("TransactionDesc")
    /**This is any additional information/comment that can be sent along with the request from your system. Maximum of 13 Characters.*/
    val transactionDesc: String,
)
