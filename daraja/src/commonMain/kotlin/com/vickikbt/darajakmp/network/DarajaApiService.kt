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

package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaPaymentRequest
import com.vickikbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaResult
import com.vickikbt.darajakmp.utils.getOrThrow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.util.encodeBase64

/**Encapsulate API calls to Daraja API
 *
 * @param [httpClient] HttpClient provided by Ktor Client
 * @param[consumerKey] Daraja API consumer key
 * @param [consumerSecret] Daraja API consumer secret
 * */
internal class DarajaApiService constructor(
    private val httpClient: HttpClient,
    private val consumerKey: String,
    private val consumerSecret: String
) {

    /**Initiate API call using the [httpClient] provided by Ktor to fetch Daraja API access token
     * of type [DarajaToken]*/
    internal suspend fun getAccessToken(): DarajaResult<DarajaToken> = darajaSafeApiCall {
        val key = "$consumerKey:$consumerSecret"
        val base64EncodedKey = key.encodeBase64()

        return@darajaSafeApiCall httpClient.get(urlString = "oauth/v1/generate?grant_type=client_credentials") {
            headers {
                append(HttpHeaders.Authorization, "Basic $base64EncodedKey")
            }
        }.body()
    }

    /**Initiate API call using the [httpClient] provided by Ktor to trigger Mpesa Express payment on Daraja API */
    internal suspend fun initiateMpesaStk(darajaPaymentRequest: DarajaPaymentRequest): DarajaResult<DarajaPaymentResponse> =
        darajaSafeApiCall {
            val accessToken = getAccessToken().getOrThrow().accessToken

            return@darajaSafeApiCall httpClient.post(urlString = "mpesa/stkpush/v1/processrequest") {
                headers { append(HttpHeaders.Authorization, "Bearer $accessToken") }
                setBody(darajaPaymentRequest)
            }.body()
        }
}
