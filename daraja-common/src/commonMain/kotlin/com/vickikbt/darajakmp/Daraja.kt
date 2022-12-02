package com.vickikbt.darajakmp

import com.vickikbt.darajakmp.network.DarajaApiService
import com.vickikbt.darajakmp.network.DarajaHttpClient
import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaEnvironment
import com.vickikbt.darajakmp.utils.DarajaTransactionType
import com.vickikbt.darajakmp.utils.getDarajaPassword
import com.vickikbt.darajakmp.utils.getDarajaPhoneNumber
import com.vickikbt.darajakmp.utils.getDarajaTimestamp
import io.ktor.client.HttpClient
import kotlinx.datetime.Clock

class Daraja private constructor(
    val consumerKey: String?,
    val consumerSecret: String?,
    val passKey: String?,
    val environment: DarajaEnvironment?
) {

    data class Builder(
        var consumerKey: String? = null,
        var consumerSecret: String? = null,
        var passKey: String? = null,
        var environment: DarajaEnvironment? = null
    ) {
        fun setConsumerKey(consumerKey: String) = apply { this.consumerKey = consumerKey }

        fun setConsumerSecret(consumerSecret: String) =
            apply { this.consumerSecret = consumerSecret }

        fun setPassKey(passKey: String) = apply { this.passKey = passKey }

        fun isSandbox(environment: DarajaEnvironment = DarajaEnvironment.SANDBOX_ENVIRONMENT) =
            apply { this.environment = environment }

        fun isProduction(environment: DarajaEnvironment = DarajaEnvironment.PRODUCTION_ENVIRONMENT) =
            apply { this.environment = environment }

        fun build(): Daraja = Daraja(
            consumerKey = consumerKey,
            consumerSecret = consumerSecret,
            passKey = passKey,
            environment = environment
        )
    }

    private val darajaHttpClient: HttpClient = DarajaHttpClient(
        environment = environment ?: DarajaEnvironment.SANDBOX_ENVIRONMENT
    ).createDarajaHttpClient()

    private val darajaApiService: DarajaApiService = DarajaApiService(
        httpClient = darajaHttpClient,
        consumerKey = consumerKey ?: "",
        consumerSecret = consumerSecret ?: ""
    )

    // ToDo: Set as internal
    // ToDo: Better way to return the result/response
    suspend fun requestAuthToken(): DarajaToken {
        return darajaApiService.getAuthToken()
    }

    suspend fun initiateDarajaStk(
        businessShortCode: String,
        amount: Int,
        phoneNumber: String,
        transactionType: DarajaTransactionType,
        transactionDesc: String,
        callbackUrl: String,
        accountReference: String? = null
    ) {
        val timestamp = Clock.System.now().getDarajaTimestamp()

        val darajaPassword = getDarajaPassword(
            shortCode = businessShortCode,
            passkey = passKey ?: "",
            timestamp = timestamp
        )

        val darajaPaymentRequest = DarajaPaymentRequest(
            businessShortCode = businessShortCode,
            password = darajaPassword,
            timestamp = timestamp,
            transactionDesc = transactionDesc,
            amount = amount.toString(),
            transactionType = transactionType.name,
            phoneNumber = phoneNumber.getDarajaPhoneNumber() ?: phoneNumber,
            callBackUrl = callbackUrl, // ToDo: Figure out how callback urls work
            accountReference = accountReference ?: businessShortCode,
            partyA = phoneNumber,
            partyB = businessShortCode
        )

        darajaApiService.requestMpesaStk(darajaPaymentRequest = darajaPaymentRequest)
    }

}