package com.vickbt.darajakmp.network.models

data class C2BRequestBody(
    val ConfirmationURL: String,
    val ResponseType: String,
    val ShortCode: Int,
    val ValidationURL: String
)
