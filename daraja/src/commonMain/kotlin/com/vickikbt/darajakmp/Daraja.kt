package com.vickikbt.darajakmp

import com.vickikbt.darajakmp.network.DarajaApiService
import com.vickikbt.darajakmp.network.DarajaHttpClientFactory
import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaEnvironment
import com.vickikbt.darajakmp.utils.DarajaTransactionType
import com.vickikbt.darajakmp.utils.getDarajaPassword
import com.vickikbt.darajakmp.utils.getDarajaPhoneNumber
import com.vickikbt.darajakmp.utils.getDarajaTimestamp
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock

class Daraja constructor(
    private val consumerKey: String?,
    private val consumerSecret: String?,
    private val passKey: String?,
    private val environment: DarajaEnvironment? = DarajaEnvironment.SANDBOX_ENVIRONMENT
) {

    data class Builder(
        private var consumerKey: String? = null,
        private var consumerSecret: String? = null,
        private var passKey: String? = null,
        private var environment: DarajaEnvironment? = null
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

    private val darajaHttpClientFactory: HttpClient = DarajaHttpClientFactory(
        environment = environment ?: DarajaEnvironment.SANDBOX_ENVIRONMENT
    ).createDarajaHttpClient()

    private val darajaApiService: DarajaApiService = DarajaApiService(
        httpClient = darajaHttpClientFactory,
        consumerKey = consumerKey ?: "",
        consumerSecret = consumerSecret ?: ""
    )

    private val defaultDispatcher = CoroutineScope(Dispatchers.Default).coroutineContext

    // ToDo: Set as internal
    // ToDo: Better way to return the result/response
    fun requestAuthToken(): Result<DarajaToken> = runBlocking {
        withContext(defaultDispatcher) {
            return@withContext darajaApiService.getAuthToken()
        }
    }

    fun initiateDarajaStk(
        businessShortCode: String,
        amount: Int,
        phoneNumber: String,
        transactionType: DarajaTransactionType,
        transactionDesc: String,
        callbackUrl: String,
        accountReference: String? = null
    ): Result<DarajaPaymentResponse> = runBlocking {
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

        withContext(defaultDispatcher) {
            return@withContext darajaApiService.initiateMpesaStk(darajaPaymentRequest = darajaPaymentRequest)
        }
    }
}
