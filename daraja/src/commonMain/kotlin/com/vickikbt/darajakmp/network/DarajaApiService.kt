package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickikbt.darajakmp.network.models.DarajaToken
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.HttpHeaders
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

internal class DarajaApiService constructor(
    private val httpClient: HttpClient,
    private val consumerKey: String,
    private val consumerSecret: String
) {

    @OptIn(InternalAPI::class)
    internal suspend fun getAuthToken(): Result<DarajaToken> = darajaSafeApiCall {
        val key = "$consumerKey:$consumerSecret"
        val base64EncodedKey = key.encodeBase64()

        return@darajaSafeApiCall httpClient.get(urlString = "oauth/v1/generate?grant_type=client_credentials") {
            headers {
                append(HttpHeaders.Authorization, "Basic $base64EncodedKey")
            }
        }
    }

    internal suspend fun initiateMpesaStk(darajaPaymentRequest: DarajaPaymentRequest): Result<DarajaPaymentResponse> =
        darajaSafeApiCall {
            val accessToken = getAuthToken().getOrThrow().accessToken

            return@darajaSafeApiCall httpClient.post(urlString = "mpesa/stkpush/v1/processrequest") {
                headers { append(HttpHeaders.Authorization, "Bearer $accessToken") }
                body = darajaPaymentRequest
            }
        }
}
