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

@ObjCName(swiftName = "C2BRequest")
@Serializable
/**Request C2B M-Pesa payment*/
internal data class C2BRequest(
    @SerialName("Amount")
    val amount: Int,

    @SerialName("BillRefNumber")
    val billReferenceNumber: String,

    @SerialName("CommandID")
    val commandID: String,

    @SerialName("Msisdn")
    val phoneNumber: Long,

    @SerialName("ShortCode")
    val shortCode: String?
)
