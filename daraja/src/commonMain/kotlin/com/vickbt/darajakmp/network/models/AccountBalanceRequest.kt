package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AccountBalanceRequest(
    @SerialName("Initiator")
    val initiator: String,

    @SerialName("SecurityCredential")
    val securityCredential: String,

    @SerialName("CommandID")
    val commandId: String,

    @SerialName("PartyA")
    val partyA: Int,

    @SerialName("IdentifierType")
    val identifierType: Int,

    @SerialName("Remarks")
    val remarks: String,

    @SerialName("QueueTimeOutURL")
    val queueTimeOutURL: String,

    @SerialName("ResultURL")
    val resultURL: String
)
