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

@ObjCName(swiftName = "DarajaTransactionResponse")
@Serializable
data class DarajaTransactionResponse(
    @SerialName("ResponseCode")
    val responseCode: String,

    @SerialName("ResponseDescription")
    val responseDescription: String,

    @SerialName("MerchantRequestID")
    val merchantRequestID: String,

    @SerialName("CheckoutRequestID")
    val checkoutRequestID: String,

    @SerialName("ResultCode")
    val resultCode: String,

    @SerialName("ResultDesc")
    val resultDescription: String
)
