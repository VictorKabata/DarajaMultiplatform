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

package com.vickikbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
/**
 * Error response from Daraja API.
 * */
data class DarajaException(
    @SerialName("requestId")
    /**This is a unique requestID for the payment request.*/
    var requestId: String? = null,

    @SerialName("errorCode")
    /**This is a predefined code that indicates the reason for request failure.*/
    var errorCode: String? = null,

    @SerialName("errorMessage")
    /**This is a short descriptive message of the failure reason.*/
    var errorMessage: String? = null
) : Exception(errorMessage)
