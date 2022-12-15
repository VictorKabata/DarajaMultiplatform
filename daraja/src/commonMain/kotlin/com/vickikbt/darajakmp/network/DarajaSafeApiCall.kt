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

import com.vickikbt.darajakmp.network.models.DarajaException
import com.vickikbt.darajakmp.utils.DarajaResult
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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
    val error = getError(e.response.body())
    DarajaResult.Failure(exception = error)
} catch (e: ClientRequestException) {
    val error = getError(e.response.body())
    DarajaResult.Failure(exception = error)
} catch (e: ServerResponseException) {
    val error = getError(e.response.body())
    DarajaResult.Failure(exception = error)
} catch (e: UnresolvedAddressException) {
    val error = getError(exception = e)
    DarajaResult.Failure(exception = error)
} catch (e: IOException) {
    val error = getError(exception = e)
    DarajaResult.Failure(exception = error)
} catch (e: SerializationException) {
    val error = getError(exception = e)
    DarajaResult.Failure(exception = error)
} catch (e: Exception) {
    val error = getError(exception = e)
    DarajaResult.Failure(exception = error)
}

/**Generate [DarajaException] from network or system error when making network calls*/
internal fun getError(
    responseContent: ByteReadChannel? = null,
    exception: Exception? = null
): DarajaException {
    return if (responseContent != null) Json.decodeFromString(string = responseContent.toString())
    else DarajaException(requestId = null, errorCode = null, errorMessage = exception?.message)
}
