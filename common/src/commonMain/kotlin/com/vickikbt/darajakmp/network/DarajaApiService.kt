package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaToken
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
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

        Napier.i("Key: $key")
        Napier.i("Encoded Key: $base64EncodedKey")

        val response =
            httpClient.get<DarajaToken>(urlString = "oauth/v1/generate?grant_type=client_credentials") {
                headers {
                    append(HttpHeaders.Authorization, "Basic $base64EncodedKey")
                }
            }

        Napier.i(tag = "Http Response", message = "$response")

        return response
    }

}