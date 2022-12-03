package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickikbt.darajakmp.network.models.DarajaToken
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

internal class DarajaApiService constructor(
    private val httpClient: HttpClient,
    private val consumerKey: String,
    private val consumerSecret: String
) {

    // ToDo: Error handling for network requests

    @OptIn(InternalAPI::class)
    internal suspend fun getAuthToken(): DarajaToken {
        val key = "$consumerKey:$consumerSecret"
        val base64EncodedKey = key.encodeBase64()

        return httpClient.get(urlString = "oauth/v1/generate?grant_type=client_credentials") {
            headers {
                append(HttpHeaders.Authorization, "Basic $base64EncodedKey")
            }
        }
    }

    internal suspend fun requestMpesaStk(darajaPaymentRequest: DarajaPaymentRequest): DarajaPaymentResponse {
        val accessToken = getAuthToken().accessToken

        return httpClient.post(urlString = "mpesa/stkpush/v1/processrequest") {
            headers { append(HttpHeaders.Authorization, "Bearer $accessToken") }

            contentType(ContentType.Application.Json)
            body = darajaPaymentRequest
        }
    }
}
