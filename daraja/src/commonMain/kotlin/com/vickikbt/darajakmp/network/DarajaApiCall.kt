package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaErrorResponse
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend fun <T : Any> darajaApiCall(apiCall: suspend () -> T): Result<T> = runCatching {
    return try {
        Result.success(apiCall.invoke())
    } catch (e: RedirectResponseException) { // 3xx errors
        Result.failure(exception = e)
    } catch (e: ClientRequestException) { // 4xx errors
        Result.failure(exception = e)
    } catch (e: ServerResponseException) { // 5xx errors
        Result.failure(exception = e)
    } catch (e: Exception) {
        Result.failure(exception = e)
    }
}

fun getError(responseContent: ByteReadChannel): DarajaErrorResponse {
    return Json.decodeFromString(string = responseContent.toString())
}
