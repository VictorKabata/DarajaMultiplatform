package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.native.ObjCName

@ObjCName(swiftName = "DynamicQrResponse")
@Serializable
/**Response body for the generated QR code.
 *
 * @param [responseCode] Used to return the Transaction Type.
 * @param [requestId]
 * @param [responseDescription] This is a response describing the status of the transaction.
 * @param [qrCode] QR Code Image/Data/String.
 * */
data class DynamicQrResponse(
    @SerialName("ResponseCode")
    val responseCode: String,

    @SerialName("RequestID")
    val requestId: String,

    @SerialName("ResponseDescription")
    val responseDescription: String,

    @SerialName("QRCode")
    val qrCode: String,
)
