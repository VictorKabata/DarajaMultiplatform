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

import com.vickbt.darajakmp.network.models.AccountBalanceRequest
import com.vickbt.darajakmp.network.models.AccountBalanceResponse
import com.vickbt.darajakmp.network.models.C2BRegistrationRequest
import com.vickbt.darajakmp.network.models.C2BRegistrationResponse
import com.vickbt.darajakmp.network.models.C2BRequest
import com.vickbt.darajakmp.network.models.C2BResponse
import com.vickbt.darajakmp.network.models.DarajaToken
import com.vickbt.darajakmp.network.models.DarajaTransactionRequest
import com.vickbt.darajakmp.network.models.DarajaTransactionResponse
import com.vickbt.darajakmp.network.models.DynamicQrRequest
import com.vickbt.darajakmp.network.models.DynamicQrResponse
import com.vickbt.darajakmp.network.models.MpesaExpressRequest
import com.vickbt.darajakmp.network.models.MpesaExpressResponse
import com.vickbt.darajakmp.network.models.QueryMpesaExpressRequest
import com.vickbt.darajakmp.network.models.QueryMpesaExpressResponse
import com.vickbt.darajakmp.utils.DarajaEndpoints
import com.vickbt.darajakmp.utils.DarajaResult
import com.vickbt.darajakmp.utils.getOrThrow
import io.github.reactivecircus.cache4k.Cache
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.util.encodeBase64
import kotlin.time.Duration.Companion.seconds

/**Encapsulate API calls to Daraja API
 *
 * @param [httpClient] HttpClient provided by Ktor Client
 * @param[consumerKey] Daraja API consumer key
 * @param [consumerSecret] Daraja API consumer secret
 * */
internal class DarajaApiService(
    private val httpClient: HttpClient,
    private val consumerKey: String,
    private val consumerSecret: String,
    private val inMemoryCache: Cache<Long, DarajaToken> =
        Cache.Builder<Long, DarajaToken>()
            .expireAfterWrite(3600.seconds).build(),
) {
    /** Initiate API call using the [httpClient] provided by Ktor to fetch Daraja API access token
     * of type [DarajaToken]*/
    internal suspend fun fetchAccessToken(): DarajaResult<DarajaToken> =
        darajaSafeApiCall {
            val key = "$consumerKey:$consumerSecret"
            val base64EncodedKey = key.encodeBase64()

            val accessToken =
                httpClient.get(urlString = DarajaEndpoints.REQUEST_ACCESS_TOKEN) {
                    headers {
                        append(HttpHeaders.Authorization, "Basic $base64EncodedKey")
                    }
                }.body<DarajaToken>().also { darajaToken ->
                    inMemoryCache.put(key = 1, value = darajaToken)
                }

            return@darajaSafeApiCall accessToken
        }

    /**Initiate API call using the [httpClient] provided by Ktor to trigger Mpesa Express payment on Daraja API */
    internal suspend fun initiateMpesaExpress(mpesaExpressRequest: MpesaExpressRequest): DarajaResult<MpesaExpressResponse> =
        darajaSafeApiCall {
            val accessToken =
                inMemoryCache.get(1) {
                    fetchAccessToken().getOrThrow()
                }

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.INITIATE_MPESA_EXPRESS) {
                headers { append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}") }
                setBody(mpesaExpressRequest)
            }.body()
        }

    internal suspend fun queryMpesaExpress(queryMpesaExpressRequest: QueryMpesaExpressRequest): DarajaResult<QueryMpesaExpressResponse> =
        darajaSafeApiCall {
            val accessToken =
                inMemoryCache.get(1) {
                    fetchAccessToken().getOrThrow()
                }

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.QUERY_MPESA_EXPRESS) {
                headers { append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}") }
                setBody(queryMpesaExpressRequest)
            }.body()
        }

    internal suspend fun generateDynamicQr(dynamicQrRequest: DynamicQrRequest): DarajaResult<DynamicQrResponse> =
        darajaSafeApiCall {
            val accessToken =
                inMemoryCache.get(1) {
                    fetchAccessToken().getOrThrow()
                }

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.DYNAMIC_QR) {
                headers { append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}") }
                setBody(dynamicQrRequest)
            }.body()
        }

    /**Initiate API call using the [httpClient] provided by Ktor to query the status of an Mpesa Express payment transaction*/
    internal suspend fun queryTransaction(darajaTransactionRequest: DarajaTransactionRequest): DarajaResult<DarajaTransactionResponse> =
        darajaSafeApiCall {
            val key = "$consumerKey:$consumerSecret"
            val base64EncodedKey = key.encodeBase64()

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.QUERY_MPESA_TRANSACTION) {
                headers { append(HttpHeaders.Authorization, "Basic $base64EncodedKey") }
                setBody(darajaTransactionRequest)
            }.body()
        }

    suspend fun c2bRegistration(c2bRegistrationRequest: C2BRegistrationRequest): DarajaResult<C2BRegistrationResponse> =
        darajaSafeApiCall {
            val accessToken =
                inMemoryCache.get(1) {
                    fetchAccessToken().getOrThrow()
                }

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.C2B_REGISTRATION_URL) {
                headers { append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}") }
                setBody(c2bRegistrationRequest)
            }.body()
        }

    internal suspend fun c2b(c2bRequest: C2BRequest): DarajaResult<C2BResponse> =
        darajaSafeApiCall {
            val accessToken =
                inMemoryCache.get(1) {
                    fetchAccessToken().getOrThrow()
                }

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.INITIATE_C2B) {
                headers { append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}") }
                setBody(c2bRequest)
            }.body()
        }

    internal suspend fun accountBalance(accountBalanceRequest: AccountBalanceRequest): DarajaResult<AccountBalanceResponse> =
        darajaSafeApiCall {
            val accessToken =
                inMemoryCache.get(1) {
                    fetchAccessToken().getOrThrow()
                }

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.ACCOUNT_BALANCE) {
                headers { append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}") }
                setBody(accountBalanceRequest)
            }.body()
        }
}
