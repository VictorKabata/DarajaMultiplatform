package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.native.ObjCName


@ObjCName(swiftName = "DynamicQrRequest")
@Serializable
/**Request body to generate dynamic qr code
 *
 * @param [merchantName] Name of the company/M-Pesa merchant name
 * @param referenceNumber Transaction reference
 * @param amount The total amount for the sale/transaction.
 * @param transactionCode Transaction Type. The supported types are:
 * BG: Pay Merchant (Buy Goods).
 *
 * WA: Withdraw Cash at Agent Till.
 *
 * PB: Paybill or Business number.
 *
 * SM: Send Money(Mobile number)
 *
 * SB: Sent to Business. Business number CPI in MSISDN format.
 * @param cpi Credit Party Identifier. Can be a mobile number, business number, agent till, paybill or business number, or merchant buy goods.
 * @param size Size of the QR code image in pixels. QR code image will always be a square image.
 * */
internal data class DynamicQrRequest(
    @SerialName("MerchantName")
    val merchantName: String,

    @SerialName("RefNo")
    val referenceNumber: String,

    @SerialName("Amount")
    val amount: Int,

    @SerialName("TrxCode")
    val transactionCode: String,

    @SerialName("CPI")
    val cpi: String,

    @SerialName("Size")
    val size: String
)
