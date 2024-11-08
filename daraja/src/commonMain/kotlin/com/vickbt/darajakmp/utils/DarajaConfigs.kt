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

package com.vickbt.darajakmp.utils

internal object DarajaEndpoints {
    const val PROD_BASE_URL = "api.safaricom.co.ke"
    const val SANDBOX_BASE_URL = "sandbox.safaricom.co.ke"

    const val REQUEST_ACCESS_TOKEN = "oauth/v1/generate?grant_type=client_credentials"
    const val INITIATE_MPESA_EXPRESS = "mpesa/stkpush/v1/processrequest"
    const val QUERY_MPESA_EXPRESS = "mpesa/stkpushquery/v1/query"
    const val QUERY_MPESA_TRANSACTION = "mpesa/stkpushquery/v1/query"
    const val C2B_REGISTRATION_URL = "mpesa/c2b/v1/registerurl"
    const val INITIATE_C2B = "mpesa/c2b/v1/simulate"
    const val DYNAMIC_QR = "mpesa/qrcode/v1/generate"
    const val ACCOUNT_BALANCE = "mpesa/accountbalance/v1/query"
}

enum class DarajaTransactionType {
    CustomerPayBillOnline,
    CustomerBuyGoodsOnline,
}

enum class DarajaEnvironment {
    PRODUCTION_ENVIRONMENT,
    SANDBOX_ENVIRONMENT,
}

internal enum class C2BResponseType {
    CANCELED,
    COMPLETED,
}

/**
 * @param [BG] Pay Merchant (Buy Goods).
 *
 * @param [WA] Withdraw Cash at Agent Till.
 *
 * @param [PB] Paybill or Business number.
 *
 * @param [SM] Send Money(Mobile number)
 *
 * @param [SB] Sent to Business. Business number CPI in MSISDN format.*/
internal enum class DarajaTransactionCode {
    BG,
    WA,
    PB,
    SM,
    SB,
}

internal enum class DarajaIdentifierType {
    TILL_NUMBER,
    SHORT_CODE,
}
