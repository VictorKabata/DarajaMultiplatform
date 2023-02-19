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

package com.vickbt.darajakmp.network.models

import kotlin.native.ObjCName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ObjCName(swiftName = "DarajaPaymentRequest")
@Serializable
/**
 * Request body sent to Daraja API to request Mpesa Express payment.
 * */
data class DarajaPaymentRequest(

    /**This is organizations shortcode (Paybill or Buygoods - A 5 to 7 digit account number) used to identify an organization and receive the transaction.*/
    @SerialName("BusinessShortCode")
    val businessShortCode: String,

    /**This is the password used for encrypting the request sent: A base64 encoded string. (The base64 string is a combination of [Shortcode]+[Passkey]+[Timestamp]).*/
    @SerialName("Password")
    val password: String,

    /**The mobile number to receive the STK pin prompt. This is the same as [partyA].*/
    @SerialName("PhoneNumber")
    val phoneNumber: String,

    /**This is the Timestamp of the transaction in the format of YEAR+MONTH+DATE+HOUR+MINUTE+SECOND (YYYYMMDDHHMMSS).*/
    @SerialName("Timestamp")
    val timestamp: String,

    /**This is the transaction type that is used to identify the transaction when sending the request to M-Pesa.*/
    @SerialName("TransactionType")
    val transactionType: String,

    /**Money that customer pays to the [businessShortCode].*/
    @SerialName("Amount")
    val amount: String,

    /**The phone number sending money.*/
    @SerialName("PartyA") // 🥳
    val partyA: String,

    /**The organization receiving the funds. This is the same as [businessShortCode].*/
    @SerialName("PartyB")
    val partyB: String,

    /**This is a valid secure URL that is used to receive notifications from M-Pesa API. It is the endpoint to which the results will be sent by M-Pesa API.*/
    @SerialName("CallBackURL")
    val callBackUrl: String,

    /**This is an alpha-numeric parameter that is defined by your system as an Identifier of the transaction for CustomerPayBillOnline transaction type.*/
    @SerialName("AccountReference")
    val accountReference: String,

    /**This is any additional information/comment that can be sent along with the request from your system. Maximum of 13 Characters.*/
    @SerialName("TransactionDesc")
    val transactionDesc: String
)
