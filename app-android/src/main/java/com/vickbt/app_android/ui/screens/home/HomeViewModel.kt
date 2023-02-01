/*
 * Copyright 2022 Daraja Multiplatform
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

package com.vickbt.app_android.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickbt.darajakmp.Daraja
import com.vickbt.darajakmp.network.models.DarajaPaymentResponse
import com.vickbt.darajakmp.utils.DarajaResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val daraja: Daraja) : ViewModel() {

    private val _mpesaResponse = MutableStateFlow<DarajaResult<DarajaPaymentResponse>?>(null)
    val mpesaResponse get() = _mpesaResponse.asStateFlow()

    fun initiateMpesaPayment(
        businessShortCode: String,
        amount: Int,
        phoneNumber: String,
        transactionDesc: String,
        callbackUrl: String,
        accountReference: String
    ) = viewModelScope.launch {
        val response = daraja.initiateMpesaExpressPayment(
            businessShortCode = businessShortCode.trim(),
            amount = amount,
            phoneNumber = phoneNumber.trim(),
            transactionDesc = transactionDesc,
            callbackUrl = callbackUrl.trim(),
            accountReference = accountReference.trim()
        )

        _mpesaResponse.value = response
    }
}
