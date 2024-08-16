/*
 * Copyright 2023 Daraja Multiplatform
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

@ObjCName(swiftName = "C2BResponse")
@Serializable
/***/
internal data class C2BResponse(
    /**This is a global unique identifier for the transaction request returned by the API proxy upon successful request submission.*/
    @SerialName("OriginatorCoversationID")
    val originatorCoversationId: String,
    /**It indicates whether Mobile Money accepts the request or not.*/
    @SerialName("ResponseCode")
    val responseCode: String,
    /**This is the status of the request.*/
    @SerialName("ResponseDescription")
    val responseDescription: String,
)
