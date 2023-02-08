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

package com.vickbt.darajakmp.network.models

const val AccessToken200JSON = """   
{
  "access_token": "wWAHdtiE4GCSGv2ocfzQ0WHefwAJ",
  "expires_in": "3599"
}
"""

const val AccessToken400JSON = """
    {
	"requestId": "43301-58413611-1",
	"errorCode": "400.008.01",
	"errorMessage": "Invalid Authentication passed"
}
"""

const val MpesaExpress200JSON = """
{
  "MerchantRequestID": "6093-85819535-1",
  "CheckoutRequestID": "ws_CO_16122022001707470708374149",
  "ResponseCode": "0",
  "ResponseDescription": "Success. Request accepted for processing",
  "CustomerMessage": "Success. Request accepted for processing"
}
"""

const val MpesaExpress500JSON = """
{
	"requestId": "119414-258858845-1",
	"errorCode": "500.001.1001",
	"errorMessage": "Unable to lock subscriber, a transaction is already in process for the current subscriber"
}
"""

const val InvalidAccessTokenJSON = """
{
  "requestId": "16813-15-1",
  "errorCode": "404.001.04",
  "errorMessage": "Invalid Access Token"
}
"""

const val QueryTransaction200JSON = """
{
  "ResponseCode": "0",
  "ResponseDescription": "The service request has been accepted successsfully",
  "MerchantRequestID": "15386-269505584-1",
  "CheckoutRequestID": "ws_CO_07022023155508743714091304",
  "ResultCode": "0",
  "ResultDesc": "The service request is processed successfully."
}
"""
