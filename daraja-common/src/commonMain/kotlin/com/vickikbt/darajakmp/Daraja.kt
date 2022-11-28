package com.vickikbt.darajakmp

import com.vickikbt.darajakmp.network.DarajaApiService
import com.vickikbt.darajakmp.network.DarajaHttpClient
import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaEnvironment
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
        password: String,
        transactionDesc: String,
        amount: String,
        transactionType: String,
        partyA: String,
        partyB: String,
        phoneNumber: String,
        callbackUrl: String,
        accountReference: String
    ) {
        val darajaPaymentRequest = DarajaPaymentRequest(
            businessShortCode = businessShortCode,
            password = password, // ToDo: Hash password
            timestamp = "", // ToDo: Get daraja timestamp
            transactionDesc = transactionDesc,
            amount = amount,
            transactionType = transactionType, // ToDo: Create transaction type enums
            partyA = partyA,
            partyB = partyB,
            phoneNumber = phoneNumber, // ToDo: Format phone number
            callBackUrl = callbackUrl, // ToDo: Figure out how callback urls work
            accountReference = accountReference
        )
        darajaApiService.requestMpesaStk(darajaPaymentRequest = darajaPaymentRequest)
    }

}