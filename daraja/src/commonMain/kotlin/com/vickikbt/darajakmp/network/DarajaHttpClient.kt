package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.utils.DarajaConstants
import com.vickikbt.darajakmp.utils.DarajaEnvironment
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

internal class DarajaHttpClient constructor(private val environment: DarajaEnvironment) {

    private val BASE_URL = if (environment == DarajaEnvironment.SANDBOX_ENVIRONMENT) {
        DarajaConstants.SANDBOX_BASE_URL
    } else {
        DarajaConstants.PROD_BASE_URL
    }

    /*Initialize Http Client responsible for handling network operations*/
    internal fun createDarajaHttpClient(): HttpClient {
        val client: HttpClient = HttpClient(engineFactory = CIO) {
            defaultRequest {
                url {
                    host = BASE_URL
                    url { protocol = URLProtocol.HTTPS }
                }
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.i(tag = "Http Client", message = message)
                    }
                }
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }

        return client
    }
}