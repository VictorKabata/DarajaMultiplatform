package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.native.ObjCName

@ObjCName(swiftName = "C2BRequest")
@Serializable
/**Request C2B M-Pesa payment*/
internal data class C2BRequest(
    @SerialName("Amount")
    val amount: Int,

    @SerialName("BillRefNumber")
    val billReferenceNumber: String,

    @SerialName("CommandID")
    val commandID: String,

    @SerialName("Msisdn")
    val phoneNumber: Long,

    @SerialName("ShortCode")
    val shortCode: String?
)
