package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json

object NetworkClient {

    fun createHttpClient(): HttpClient {
        val client: HttpClient = HttpClient {

            defaultRequest {
                url {
                    host = Constants.SANDBOX_BASE_URL // ToDo
                    url { protocol = URLProtocol.HTTPS }
                }
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        // Napier.e(tag = "Http Client", message = message) ToDo
                        println("Http Client: $message")
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