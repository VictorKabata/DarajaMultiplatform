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

@ObjCName(swiftName = "DarajaPaymentResponse")
@Serializable
/**
 * Response returned by Daraja API on successful Mpesa Express payment initiation.
 * */
data class DarajaPaymentResponse(
    /**This is a global unique Identifier for any submitted payment request.*/
    @SerialName("MerchantRequestID")
    var merchantRequestID: String,

    /**This is a global unique identifier of the processed checkout transaction request*/
    @SerialName("CheckoutRequestID")
    var checkoutRequestID: String,

    /**This is a Numeric status code that indicates the status of the transaction submission. 0 means successful submission and any other code means an error occurred.*/
    @SerialName("ResponseCode")
    var responseCode: String,

    /**This is an acknowledgment message from the API that gives the status of the request submission.*/
    @SerialName("ResponseDescription")
    var responseDescription: String,

    /**This is a message that your system can display to the Customer as an acknowledgement of the payment request submission.*/
    @SerialName("CustomerMessage")
    var customerMessage: String
)
