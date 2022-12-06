package com.vickikbt.app_android.ui.screens.home

import com.vickikbt.darajakmp.Daraja
import com.vickikbt.darajakmp.network.models.DarajaPaymentResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel constructor(private val daraja: Daraja) {

    private val _mpesaResponse = MutableStateFlow<DarajaPaymentResponse?>(null)
    val mpesaResponse get() = _mpesaResponse.asStateFlow()

    /*fun initiateMpesaPayment(
        businessShortCode: String,
        amount: Int,
        phoneNumber: String,
        transactionType: DarajaTransactionType,
        transactionDesc: String,
        callbackUrl: String,
        accountReference: String
    ) = viewModelScope.launch {
        try {
            println("Initiating mpesa payment")

            val response = daraja.initiateDarajaStk(
                businessShortCode = businessShortCode,
                amount = amount,
                phoneNumber = phoneNumber,
                transactionType = transactionType,
                transactionDesc = transactionDesc,
                callbackUrl = callbackUrl,
                accountReference = accountReference
            )
            _mpesaResponse.value = response
        } catch (e: Exception) {
            println("Initiating mpesa payment error: $e")
            _mpesaResponse.value = null
        }
    }*/
}
