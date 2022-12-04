package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaErrorResponse
import io.ktor.client.features.ResponseException
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend fun <T : Any> darajaSafeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.success(apiCall.invoke())
    } catch (e: ResponseException) { // Non-200 error
        Result.failure(exception = e)
    } catch (e: UnresolvedAddressException) {
        Result.failure(exception = e)
    } catch (e: Exception) {
        Result.failure(exception = e)
    } catch (t: Throwable) {
        Result.failure(exception = t)
    }
}

fun getError(responseContent: ByteReadChannel): Exception {
    val response = Json.decodeFromString<DarajaErrorResponse>(string = responseContent.toString())
    return Exception(response.errorMessage)
}
