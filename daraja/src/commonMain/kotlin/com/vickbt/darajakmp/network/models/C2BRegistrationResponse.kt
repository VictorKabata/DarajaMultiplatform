package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.native.ObjCName

@ObjCName("C2BRegistrationRequest")
@Serializable
data class C2BRegistrationResponse(
    @SerialName("OriginatorCoversationID")
    val originatorConversationID: String,

    @SerialName("ResponseCode")
    val responseCode: String,

    @SerialName("ResponseDescription")
    val responseDescription: String
)
