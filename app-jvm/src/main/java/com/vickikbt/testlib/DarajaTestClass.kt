package com.vickikbt.testlib

import com.vickikbt.darajakmp.Daraja
import com.vickikbt.darajakmp.utils.DarajaTransactionType
import io.github.aakira.napier.Napier

fun main() {
    Napier.i("Starting JVM app...")

    val daraja = Daraja.Builder()
        .setConsumerKey("NrF3UW9YCIUeTeLeamBC9HRjlaGkw6RZ")
        .setConsumerSecret("lARzdAdZaRAtrXZ0")
        .setPassKey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919")
        .build()

    val mpesaStkResponse = daraja.initiateDarajaStk(
        businessShortCode = "174379",
        amount = 1,
        phoneNumber = "0714091304",
        transactionType = DarajaTransactionType.CustomerBuyGoodsOnline,
        transactionDesc = "Mpesa payment",
        callbackUrl = "https://mydomain.com/path"
    )

    Napier.i("Mpesa STK response: $mpesaStkResponse")
}
