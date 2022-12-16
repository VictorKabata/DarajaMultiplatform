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

package com.vickikbt.darajakmp.network.models

const val AccessTokenSuccessJSON = """   
{
  "access_token": "wWAHdtiE4GCSGv2ocfzQ0WHefwAJ",
  "expires_in": "3599"
}
"""

const val MpesaExpressSuccessJSON = """
{
  "MerchantRequestID": "6093-85819535-1",
  "CheckoutRequestID": "ws_CO_16122022001707470708374149",
  "ResponseCode": "0",
  "ResponseDescription": "Success. Request accepted for processing",
  "CustomerMessage": "Success. Request accepted for processing"
}
"""

const val MpesaExpressFailureJSON = """
{
  "requestId": "16813-15-1",
  "errorCode": "404.001.04",
  "errorMessage": "Invalid Access Token",
}
"""
