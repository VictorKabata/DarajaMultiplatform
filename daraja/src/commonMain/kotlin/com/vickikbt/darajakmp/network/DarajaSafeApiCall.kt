package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaErrorResponse
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

//    Result.success(apiCall.invoke())
} catch (e: RedirectResponseException) {
    val error = getError(e.response.body())
    DarajaResult.Failure(exception = error)

//    Result.failure(e)
} catch (e: ClientRequestException) {
    val error = getError(e.response.body())
    DarajaResult.Failure(exception = error)

//    Result.failure(e)
} catch (e: ServerResponseException) {
    val error = getError(e.response.body())
    DarajaResult.Failure(exception = error)

//    Result.failure(e)
} catch (e: IOException) {
    val error = getError(exception = e)
    DarajaResult.Failure(exception = error)

//    Result.failure(e)
} catch (e: SerializationException) {
    val error = getError(exception = e)
    DarajaResult.Failure(exception = error)

//    Result.failure(e)
} catch (e: Exception) {
    val error = getError(exception = e)
    DarajaResult.Failure(exception = error)

//    Result.failure(e)
}

fun getError(
    responseContent: ByteReadChannel? = null,
    exception: Exception? = null
): DarajaErrorResponse {
    return if (responseContent != null) Json.decodeFromString(string = responseContent.toString())
    else DarajaErrorResponse(requestId = null, errorCode = null, errorMessage = exception?.message)

}
