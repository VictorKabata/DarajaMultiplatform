package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.native.ObjCName

@ObjCName(swiftName = "C2BRegistrationResponse")
@Serializable
/***/
data class C2BRegistrationResponse(
    /**This is a global unique identifier for the transaction request returned by the API proxy upon successful request submission.*/
    @SerialName("OriginatorCoversationID")
    val originatorCoversationId: String,

    /**It indicates whether Mobile Money accepts the request or not.*/
    @SerialName("ResponseCode")
    val responseCode: String,

    /**This is the status of the request.*/
    @SerialName("ResponseDescription")
    val responseDescription: String
)
