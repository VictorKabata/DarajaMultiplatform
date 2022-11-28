package com.vickikbt.darajakmp

import com.vickikbt.darajakmp.network.DarajaApiService
import com.vickikbt.darajakmp.network.DarajaHttpClient
import com.vickikbt.darajakmp.network.models.DarajaToken
import com.vickikbt.darajakmp.utils.DarajaEnvironment
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Daraja(
    private val consumerKey: String,
    private val consumerSecret: String,
    private val environment: DarajaEnvironment = DarajaEnvironment.SANDBOX_ENVIRONMENT
) {

    init {
        Napier.i("Created daraja instance...")
    }

    private val darajaHttpClient: HttpClient =
        DarajaHttpClient(environment = environment).createDarajaHttpClient()
    private val darajaApiService: DarajaApiService = DarajaApiService(
        httpClient = darajaHttpClient,
        consumerKey = consumerKey,
        consumerSecret = consumerSecret
    )

    // ToDo: Set as internal
    // ToDo: Better way to return the result/response
    suspend fun requestAuthToken(): DarajaToken {
        return darajaApiService.getAuthToken()
    }

}

fun some(): Flow<List<String>> {
    return flowOf(listOf())
}