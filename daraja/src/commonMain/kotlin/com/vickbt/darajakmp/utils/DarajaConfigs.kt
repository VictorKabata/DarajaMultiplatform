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
    const val QUERY_MPESA_TRANSACTION = "mpesa/stkpushquery/v1/query"
}

enum class DarajaTransactionType {
    CustomerPayBillOnline, CustomerBuyGoodsOnline
}

enum class DarajaEnvironment {
    PRODUCTION_ENVIRONMENT, SANDBOX_ENVIRONMENT
}
