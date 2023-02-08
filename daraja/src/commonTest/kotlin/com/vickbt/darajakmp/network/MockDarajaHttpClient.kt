/*
 * Copyright 2022 Daraja Multiplatform
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vickbt.darajakmp.network

import com.vickbt.darajakmp.network.models.AccessToken200JSON
import com.vickbt.darajakmp.network.models.MpesaExpress200JSON
import com.vickbt.darajakmp.network.models.QueryTransaction200JSON
import com.vickbt.darajakmp.utils.DarajaEndpoints
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
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class MockDarajaHttpClient {

    private var httpStatusCode: HttpStatusCode = HttpStatusCode.OK
    private var responseContent: String? = null
    fun throwError(httpStatus: HttpStatusCode, response: String) {
        httpStatusCode = httpStatus
        responseContent = response
    }

    private val responseHeaders = headersOf(HttpHeaders.ContentType, "application/json")

    val mockDarajaHttpClient = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url.fullPath) {
                    "/${DarajaEndpoints.REQUEST_ACCESS_TOKEN}" -> {
                        respond(
                            responseContent ?: AccessToken200JSON, httpStatusCode, responseHeaders
                        )
                    }
                    "/${DarajaEndpoints.INITIATE_MPESA_EXPRESS}" -> {
                        respond(
                            responseContent ?: MpesaExpress200JSON, httpStatusCode, responseHeaders
                        )
                    }
                    "/${DarajaEndpoints.QUERY_MPESA_TRANSACTION}" -> {
                        respond(
                            responseContent ?: QueryTransaction200JSON,
                            httpStatusCode,
                            responseHeaders
                        )
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
}
