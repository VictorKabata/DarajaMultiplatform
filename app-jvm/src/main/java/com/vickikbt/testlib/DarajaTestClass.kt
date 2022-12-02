package com.vickikbt.testlib

import com.vickikbt.darajakmp.Daraja
import com.vickikbt.darajakmp.utils.DarajaTransactionType
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.runBlocking

fun main() {
    Napier.base(DebugAntilog())

    Napier.i("Starting JVM app...")

    val daraja = Daraja.Builder()
        .setConsumerKey("NrF3UW9YCIUeTeLeamBC9HRjlaGkw6RZ")
        .setConsumerSecret("lARzdAdZaRAtrXZ0")
        .setPassKey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919")
        .build()

    val mpesaStkResponse = runBlocking {
        daraja.initiateDarajaStk(
            businessShortCode = "174379",
            transactionDesc = "Mpesa payment",
            amount = 1,
            transactionType = DarajaTransactionType.CustomerBuyGoodsOnline,
            callbackUrl = "https://mydomain.com/path",
            phoneNumber = "0714091304"
        )
    }

    Napier.i("Mpesa STK response: $mpesaStkResponse")

}