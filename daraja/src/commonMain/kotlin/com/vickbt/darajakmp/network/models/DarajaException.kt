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

import kotlin.native.ObjCName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@ObjCName(swiftName = "DarajaException")
@Serializable
/**
 * Error response from Daraja API.
 * */
data class DarajaException(
    /**This is a unique requestID for the payment request.*/
    @SerialName("requestId")
    var requestId: String? = "0",

    /**This is a predefined code that indicates the reason for request failure.*/
    @SerialName("errorCode")
    var errorCode: String? = "0",

    /**This is a short descriptive message of the failure reason.*/
    @SerialName("errorMessage")
    var errorMessage: String? = null
) : Exception(errorMessage)
