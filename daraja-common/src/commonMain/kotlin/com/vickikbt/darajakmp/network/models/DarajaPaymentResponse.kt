package com.vickikbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DarajaPaymentResponse(
    @SerialName("MerchantRequestID")
    var MerchantRequestID: String,

    @SerialName("CheckoutRequestID")
    var CheckoutRequestID: String,

    @SerialName("ResponseCode")
    var ResponseCode: String,

    @SerialName("ResponseDescription")
    var ResponseDescription: String,

    @SerialName("CustomerMessage")
    var CustomerMessage: String
)