package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.AccessTokenSuccessJSON
import com.vickikbt.darajakmp.network.models.MpesaExpressSuccessJSON
import com.vickikbt.darajakmp.utils.DarajaEndpoints
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private val responseHeaders = headersOf(HttpHeaders.ContentType, "application/json")

val mockDarajaHttpClient = HttpClient(MockEngine) {
    engine {
        addHandler { request ->
            println("Request: ${request.url.encodedPathAndQuery}")

            when {
                request.url.encodedPathAndQuery.contains(DarajaEndpoints.REQUEST_ACCESS_TOKEN) -> {
                    respond(AccessTokenSuccessJSON, HttpStatusCode.OK, responseHeaders)
                }
                request.url.encodedPathAndQuery.contains(DarajaEndpoints.INITIATE_MPESA_EXPRESS) -> {
                    respond(MpesaExpressSuccessJSON, HttpStatusCode.OK, responseHeaders)
                }
                else -> {
                    error("Unhandled ${request.url.encodedPathAndQuery}")
                }
            }
        }
    }

    expectSuccess = true
    addDefaultResponseValidation()

    defaultRequest { contentType(ContentType.Application.Json) }

    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
        )
    }

    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                println("Http Logs: $message")
            }
        }
    }
}
