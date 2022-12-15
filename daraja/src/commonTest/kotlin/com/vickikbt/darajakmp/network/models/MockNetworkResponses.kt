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
