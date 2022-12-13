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
/**Request body sent to Daraja API to request Mpesa Express payment*/
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

    @SerialName("PartyA") // 🥳
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
