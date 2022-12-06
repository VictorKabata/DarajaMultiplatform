package com.vickikbt.darajakmp.utils

import com.vickikbt.darajakmp.network.models.DarajaErrorResponse

sealed class DarajaResult<out T : Any> {
    data class success<out T : Any>(val data: T) : DarajaResult<T>()
    data class failure(val exception: DarajaErrorResponse) : DarajaResult<Nothing>()
    object isLoading : DarajaResult<Nothing>()
}

// ToDo: Add these extension functions
fun <T : Any> DarajaResult<T>.onSuccess(action: (value: T) -> Unit): DarajaResult<T> {
    return this
}

fun <T : DarajaErrorResponse> DarajaResult<T>.onFailure(action: (error: DarajaErrorResponse) -> Unit): DarajaResult<T> {
    return this
}
