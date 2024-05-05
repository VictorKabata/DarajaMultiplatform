package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountBalanceResponse(
    @SerialName("OriginatorConversationID")
    val originatorConversationId: String,

    @SerialName("ConversationID")
    val conversationId: String,

    @SerialName("ResponseCode")
    val responseCode: String,

    @SerialName("ResponseDescription")
    val responseDescription: String
)
