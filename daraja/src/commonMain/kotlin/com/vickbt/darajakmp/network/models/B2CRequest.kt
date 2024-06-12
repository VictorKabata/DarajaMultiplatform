package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class B2CRequest(
    @SerialName("OriginatorConversationID")
    val originatorConversationID: String,

    @SerialName("InitiatorName")
    val initiatorName: String,

    @SerialName("SecurityCredential")
    val securityCredential: String,

    @SerialName("CommandID")
    val commandId: String,

    @SerialName("Amount")
    val amount: String,

    @SerialName("PartyA")
    val partyA: String,

    @SerialName("PartyB")
    val partyB: String,

    @SerialName("Remarks")
    val remarks: String,

    @SerialName("QueueTimeOutURL")
    val queueTimeOutURL: String,

    @SerialName("ResultURL")
    val resultURL: String,

    @SerialName("Occassion")
    val occassion: String
)
