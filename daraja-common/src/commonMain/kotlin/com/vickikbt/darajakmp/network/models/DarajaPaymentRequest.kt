package com.vickikbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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

    @SerialName("PartyA") //🥳
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
