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

import com.vickbt.darajakmp.network.models.DarajaException
import com.vickbt.darajakmp.utils.DarajaResult
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException

/**Encapsulate network calls and handles network and system exceptions.
 *Returns an instance of [DarajaResult] with data of type [T] on success and
 * an instance of [DarajaResult] with exception of type [DarajaException] on failure
 *
 * @return [DarajaResult] Returns data of type [T] on success
 * @throws DarajaException Throws expception of type [DarajaException] on failure
 * */
internal suspend fun <T : Any> darajaSafeApiCall(apiCall: suspend () -> T): DarajaResult<T> = try {
    DarajaResult.Success(apiCall.invoke())
} catch (e: RedirectResponseException) {
    val error = parseNetworkError(e.response.body())
    DarajaResult.Failure(exception = error)
} catch (e: ClientRequestException) {
    val error = parseNetworkError(e.response.body())
    DarajaResult.Failure(exception = error)
} catch (e: ServerResponseException) {
    val error = parseNetworkError(e.response.body())
    DarajaResult.Failure(exception = error)
} catch (e: UnresolvedAddressException) {
    val error = parseNetworkError(exception = e)
    DarajaResult.Failure(exception = error)
} catch (e: Exception) {
    val error = parseNetworkError(exception = e)
    DarajaResult.Failure(exception = error)
}

/**Generate [DarajaException] from network or system error when making network calls
 *
 * @throws [DarajaException]
 * */
internal suspend fun parseNetworkError(
    errorResponse: HttpResponse? = null,
    exception: Exception? = null
): DarajaException {
    throw errorResponse?.body<DarajaException>()
        ?: DarajaException(requestId = "0", errorCode = "0", errorMessage = exception?.message)
}
