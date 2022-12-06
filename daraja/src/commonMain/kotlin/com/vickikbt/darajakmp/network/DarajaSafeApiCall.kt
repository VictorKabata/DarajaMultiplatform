package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaException
import com.vickikbt.darajakmp.utils.DarajaResult
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal suspend fun <T : Any> darajaSafeApiCall(apiCall: suspend () -> T): DarajaResult<T> = try {
    DarajaResult.Loading(isLoading = true) // ToDo: Emit as flow later

    DarajaResult.Success(apiCall.invoke())
} catch (t: Throwable) {
    // ToDo: Collect analytics data on throwable caught

    when (t) {
        is RedirectResponseException -> {
            val error = getError(t.response.body())
            DarajaResult.Failure(exception = error)
        }
        is ClientRequestException -> {
            val error = getError(t.response.body())
            DarajaResult.Failure(exception = error)
        }
        is ServerResponseException -> {
            val error = getError(t.response.body())
            DarajaResult.Failure(exception = error)
        }
        is IOException -> {
            val error = getError(exception = t)
            DarajaResult.Failure(exception = error)
        }
        is SerializationException -> {
            val error = getError(exception = t)
            DarajaResult.Failure(exception = error)
        }
        else -> {
            val error = getError(exception = Exception())
            DarajaResult.Failure(exception = error)
        }
    }

}

fun getError(
    responseContent: ByteReadChannel? = null,
    exception: Exception? = null
): DarajaException {
    return if (responseContent != null) Json.decodeFromString(string = responseContent.toString())
    else DarajaException(requestId = null, errorCode = null, errorMessage = exception?.message)

}
