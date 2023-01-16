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

import com.vickbt.darajakmp.network.models.AccessTokenSuccessJSON
import com.vickbt.darajakmp.network.models.MpesaExpressSuccessJSON
import com.vickbt.darajakmp.utils.DarajaEndpoints
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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
