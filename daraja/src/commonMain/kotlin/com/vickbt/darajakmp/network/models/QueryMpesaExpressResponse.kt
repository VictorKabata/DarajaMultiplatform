/*
 * Copyright 2024 Daraja Multiplatform
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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param [responseCode] - This is a numeric status code that indicates the status of the transaction submission. 0 means successful submission and any other code means an error occurred.
 * @param [responseDescription] - Response description is an acknowledgment message from the API that gives the status of the request submission usually maps to a specific ResponseCode value. It can be a "Success" submission message or an error description.Response description is an acknowledgment message from the API that gives the status of the request submission usually maps to a specific ResponseCode value. It can be a "Success" submission message or an error description.
 * @param [merchantRequestID] - This is a global unique Identifier for any submitted payment request.
 * @param [checkoutRequestID] - This is a global unique identifier of the processed checkout transaction request.
 * @param [resultCode] - This is a numeric status code that indicates the status of the transaction processing. 0 means successful processing and any other code means an error occurred or the transaction failed.
 * @param [resultDescription] - Response description is an acknowledgment message from the API that gives the status of the request submission usually maps to a specific ResponseCode value. It can be a "Success" submission message or an error description.
 * */
@Serializable
data class QueryMpesaExpressResponse(
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
