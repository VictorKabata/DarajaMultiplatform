package com.vickikbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val resultDescription: String,
)
