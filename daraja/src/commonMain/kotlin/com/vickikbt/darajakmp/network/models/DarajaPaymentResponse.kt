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
 * Response returned by Daraja API on successful Mpesa Express payment initiation
 *
 * @param [merchantRequestID] This is a global unique Identifier for any submitted payment request.
 * @param [checkoutRequestID] This is a global unique identifier of the processed checkout transaction request
 * @param [responseCode] This is a Numeric status code that indicates the status of the transaction submission. 0 means successful submission and any other code means an error occured.
 * @param [responseDescription] This is an acknowledgment message from the API that gives the status of the request submission
 * @param [customerMessage] This is a message that your system can display to the Customer as an acknowledgement of the payment request submission.
 * */
data class DarajaPaymentResponse(
    @SerialName("MerchantRequestID")
    var merchantRequestID: String,

    @SerialName("CheckoutRequestID")
    var checkoutRequestID: String,

    @SerialName("ResponseCode")
    var responseCode: String,

    @SerialName("ResponseDescription")
    var responseDescription: String,

    @SerialName("CustomerMessage")
    var customerMessage: String
)
