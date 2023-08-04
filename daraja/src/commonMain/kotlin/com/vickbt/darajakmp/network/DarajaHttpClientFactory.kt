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

import com.vickbt.darajakmp.utils.DarajaEndpoints
import com.vickbt.darajakmp.utils.DarajaEnvironment
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**Initialize Ktor Http Client responsible for handling network operations*/
internal class DarajaHttpClientFactory constructor(private val environment: DarajaEnvironment) {

    private val baseURL = if (environment == DarajaEnvironment.SANDBOX_ENVIRONMENT) {
        DarajaEndpoints.SANDBOX_BASE_URL
    } else {
        DarajaEndpoints.PROD_BASE_URL
    }

    /**Initialize Ktor Http Client responsible for handling network operations*/
    internal fun createDarajaHttpClient() = HttpClient {
        expectSuccess = true
        addDefaultResponseValidation()

        defaultRequest {
            contentType(ContentType.Application.Json)

            url {
                host = baseURL
                url { protocol = URLProtocol.HTTPS }
            }
        }

        install(HttpCache)

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }

        if (environment == DarajaEnvironment.SANDBOX_ENVIRONMENT) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.i(tag = "Http Client", message = message)
                    }
                }
            }.also {
                Napier.base(DebugAntilog())
            }
        }
    }
}
