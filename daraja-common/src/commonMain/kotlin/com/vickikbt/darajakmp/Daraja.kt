package com.vickikbt.darajakmp

import com.vickikbt.darajakmp.network.DarajaApiService
import com.vickikbt.darajakmp.network.DarajaHttpClient
import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaEnvironment
import com.vickikbt.darajakmp.utils.getDarajaPassword
import com.vickikbt.darajakmp.utils.getDarajaTimestamp
import io.ktor.client.HttpClient

class Daraja(
    private val consumerKey: String,
    private val consumerSecret: String,
    private val environment: DarajaEnvironment = DarajaEnvironment.SANDBOX_ENVIRONMENT
) {

    private val darajaHttpClient: HttpClient =
        DarajaHttpClient(environment = environment).createDarajaHttpClient()
    private val darajaApiService: DarajaApiService = DarajaApiService(
        httpClient = darajaHttpClient,
        consumerKey = consumerKey,
        consumerSecret = consumerSecret
    )

    // ToDo: Set as internal
    // ToDo: Better way to return the result/response
    suspend fun requestAuthToken(): DarajaToken {
        return darajaApiService.getAuthToken()
    }

    suspend fun initiateDarajaStk(
        businessShortCode: String,
        passkey: String,
        transactionDesc: String,
        amount: Int,
        transactionType: String,
        phoneNumber: String,
        callbackUrl: String,
        accountReference: String? = null
    ) {
        val timestamp = getDarajaTimestamp()

        val darajaPassword = getDarajaPassword(
            shortCode = businessShortCode,
            passkey = passkey,
            timestamp = timestamp
        )

        val darajaPaymentRequest = DarajaPaymentRequest(
            businessShortCode = businessShortCode,
            password = darajaPassword,
            timestamp = timestamp,
            transactionDesc = transactionDesc,
            amount = amount.toString(),
            transactionType = transactionType, // ToDo: Create transaction type enums
            phoneNumber = phoneNumber, // ToDo: Format phone number
            callBackUrl = callbackUrl, // ToDo: Figure out how callback urls work
            accountReference = accountReference ?: businessShortCode,
            partyA = phoneNumber,
            partyB = businessShortCode
        )
        darajaApiService.requestMpesaStk(darajaPaymentRequest = darajaPaymentRequest)
    }

}