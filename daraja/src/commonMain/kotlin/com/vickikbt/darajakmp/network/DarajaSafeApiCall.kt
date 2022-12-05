package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaErrorResponse
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.ServerResponseException
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

suspend fun <T : Any> darajaSafeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        val response = apiCall.invoke()
        Result.success(response)
    } catch (e: ClientRequestException) {
        Result.failure(e)
    } catch (e: ServerResponseException) {
        Result.failure(e)
    } catch (e: IOException) {
        Result.failure(e)
    } catch (e: SerializationException) {
        Result.failure(e)
    }
}


fun getError(responseContent: ByteReadChannel): Exception {
    val response = Json.decodeFromString<DarajaErrorResponse>(string = responseContent.toString())
    return Exception(response.errorMessage)
}
