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
