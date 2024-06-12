package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class B2CResponse(
    @SerialName("ConversationID")
    val conversationID: String,

    @SerialName("OriginatorConversationID")
    val originatorConversationID: String,

    @SerialName("ResponseCode")
    val responseCode: String,

    @SerialName("ResponseDescription")
    val responseDescription: String
)
