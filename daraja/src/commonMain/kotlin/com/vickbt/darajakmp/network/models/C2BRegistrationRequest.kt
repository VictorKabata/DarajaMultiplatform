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

@ObjCName(swiftName = "C2BRegistrationRequest")
@Serializable
/**
 * @param [confirmationURL] This is the URL that receives the confirmation request from API upon payment completion.
 * @param [validationURL] This is the URL that receives the validation request from the API upon payment submission. The validation URL is only called if the external validation on the registered shortcode is enabled. (By default External Validation is disabled).
 * @param [responseType] This parameter specifies what is to happen if for any reason the validation URL is not reachable. Note that, this is the default action value that determines what M-PESA will do in the scenario that your endpoint is unreachable or is unable to respond on time. Only two values are allowed: `Completed` or `Cancelled`. `Completed` means M-PESA will automatically complete your transaction, whereas `Cancelled` means M-PESA will automatically cancel the transaction, in the event M-PESA is unable to reach your Validation URL.
 * @param [shortCode] A unique number is tagged to an M-PESA pay bill/till number of the organization.
 * */
data class C2BRegistrationRequest(
    @SerialName("ConfirmationURL")
    val confirmationURL: String,
    @SerialName("ValidationURL")
    val validationURL: String,
    @SerialName("ResponseType")
    val responseType: String,
    @SerialName("ShortCode")
    val shortCode: String,
)
