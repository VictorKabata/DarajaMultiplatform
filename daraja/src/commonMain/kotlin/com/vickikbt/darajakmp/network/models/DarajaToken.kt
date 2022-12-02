package com.vickikbt.darajakmp.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DarajaToken(

    @SerialName("access_token")
    val accessToken: String,

    /*Token validity duration in seconds*/
    @SerialName("expires_in")
    val expiresIn: String,
)
