/*
 * Copyright 2021 Daraja Multiplatform
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

package com.vickbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.native.ObjCName

@ObjCName(swiftName = "DarajaException")
@Serializable
/**
 * Error response from Daraja API.
 *
 * @param [requestId] This is a unique requestID for the payment request.
 * @param [errorCode] This is a predefined code that indicates the reason for request failure.
 * @param [errorMessage] This is a short descriptive message of the failure reason.
 * */
data class DarajaException(
    @SerialName("requestId")
    var requestId: String? = "0",

    @SerialName("errorCode")
    var errorCode: String? = "0",

    @SerialName("errorMessage")
    var errorMessage: String? = null
) : Exception(errorMessage)
