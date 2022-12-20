package com.vickikbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryDarajaTransactionRequest(
    @SerialName("BusinessShortCode")
    val businessShortCode: String,

    @SerialName("Password")
    val password: String,

    @SerialName("Timestamp")
    val timestamp: String,

    @SerialName("CheckoutRequestID")
    val checkoutRequestID: String,
)
