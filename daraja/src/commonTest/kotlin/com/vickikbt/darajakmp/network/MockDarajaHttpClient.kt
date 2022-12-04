package com.vickikbt.darajakmp.network

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.request
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

class MockDarajaHttpClient {

    fun createEngine(){
        val mockEngine=MockEngine{request->
            respond(
                content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
    }
    
}
