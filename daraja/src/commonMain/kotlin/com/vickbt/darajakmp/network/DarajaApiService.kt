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

import com.vickbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickbt.darajakmp.network.models.DarajaToken
import com.vickbt.darajakmp.network.models.DarajaTransactionResponse
import com.vickbt.darajakmp.network.models.QueryDarajaTransactionRequest
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
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**Encapsulate API calls to Daraja API
 *
 * @param [httpClient] HttpClient provided by Ktor Client
 * @param[consumerKey] Daraja API consumer key
 * @param [consumerSecret] Daraja API consumer secret
 * */
internal class DarajaApiService constructor(
    private val httpClient: HttpClient,
    private val consumerKey: String,
    private val consumerSecret: String,
    private val inMemoryCache: Cache<Long, DarajaToken> = Cache.Builder()
        .expireAfterWrite(3600.toDuration(DurationUnit.SECONDS)).build()
) {

    /**Initiate API call using the [httpClient] provided by Ktor to fetch Daraja API access token
     * of type [DarajaToken]*/
    internal suspend fun fetchAccessToken(): DarajaResult<DarajaToken> = darajaSafeApiCall {
        val key = "$consumerKey:$consumerSecret"
        val base64EncodedKey = key.encodeBase64()

        val accessToken = httpClient.get(urlString = DarajaEndpoints.REQUEST_ACCESS_TOKEN) {
            headers {
                append(HttpHeaders.Authorization, "Basic $base64EncodedKey")
            }
        }.body<DarajaToken>().also { darajaToken ->
            inMemoryCache.put(key = 1, value = darajaToken)
        }

        return@darajaSafeApiCall accessToken
    }

    /**Initiate API call using the [httpClient] provided by Ktor to trigger Mpesa Express payment on Daraja API */
    internal suspend fun initiateMpesaStk(darajaPaymentRequest: DarajaPaymentRequest): DarajaResult<DarajaPaymentResponse> =
        darajaSafeApiCall {
            val accessToken = inMemoryCache.get(1) {
                fetchAccessToken().getOrThrow()
            }

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.INITIATE_MPESA_EXPRESS) {
                headers { append(HttpHeaders.Authorization, "Bearer ${accessToken.accessToken}") }
                setBody(darajaPaymentRequest)
            }.body()
        }

    /**Initiate API call using the [httpClient] provided by Ktor to query the status of an Mpesa Express payment transaction*/
    internal suspend fun queryTransaction(queryDarajaTransactionRequest: QueryDarajaTransactionRequest): DarajaResult<DarajaTransactionResponse> =
        darajaSafeApiCall {
            val key = "$consumerKey:$consumerSecret"
            val base64EncodedKey = key.encodeBase64()

            return@darajaSafeApiCall httpClient.post(urlString = DarajaEndpoints.QUERY_MPESA_TRANSACTION) {
                headers { append(HttpHeaders.Authorization, "Basic $base64EncodedKey") }
                setBody(queryDarajaTransactionRequest)
            }.body()
        }
}
