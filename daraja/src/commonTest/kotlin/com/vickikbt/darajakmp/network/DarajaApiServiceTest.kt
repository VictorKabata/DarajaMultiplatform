package com.vickikbt.darajakmp.network

import com.vickikbt.darajakmp.network.models.DarajaToken
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.mockk.coEvery
import io.mockk.unmockkAll
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class DarajaApiServiceTest {

    private lateinit var mockHttpClient: HttpClient
    private lateinit var darajaApiService: DarajaApiService

    private val authToken: DarajaToken =
        DarajaToken(accessToken = "access_token", expiresIn = "expires_in")

    @BeforeTest
    fun setup() {
        mockHttpClient = HttpClient(MockEngine.create())

        darajaApiService =
            DarajaApiService(
                httpClient = mockHttpClient,
                consumerKey = "consumer_key",
                consumerSecret = "consumer_secret"
            )
    }

    @AfterTest
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun getAuthToken_returns_result_success_on_success() = runTest {
        // given
        coEvery { darajaApiService.getAuthToken() } returns Result.success(authToken)

        //when
        val result = darajaApiService.getAuthToken()

        //then
        assertTrue(actual = result.isSuccess)
        assertEquals(expected = Result.success(authToken), actual = result)
    }

    @Test
    fun getAuthToken_returns_result_error_on_error() = runTest {
        // given
        val exception = Exception()
        coEvery { darajaApiService.getAuthToken() } throws exception

        //when
        val result = darajaApiService.getAuthToken()

        //then
        assertTrue(actual = result.isFailure)
        assertEquals(expected = Result.failure(exception), actual = result)
    }

}
