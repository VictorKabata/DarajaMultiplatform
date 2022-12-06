package com.vickikbt.darajakmp.utils

import com.vickikbt.darajakmp.network.models.DarajaErrorResponse

sealed class DarajaResult<out T> {
    data class Success<out T : Any>(val data: T) : DarajaResult<T>()
    data class Failure(val exception: DarajaErrorResponse) : DarajaResult<Nothing>()
    data class Loading<out T : Boolean>(val isLoading: T) : DarajaResult<T>()
}

fun <T : Any> DarajaResult<T>.getOrNull(): T? {
    return if (this is DarajaResult.Success) this.data
    else null
}

// val some = Result.success()

// ToDo: DarajaResult for loading state
fun <T : Any> DarajaResult<T>.isLoading(data: () -> Unit): DarajaResult.Loading<Boolean> {
    return if (this is DarajaResult.Loading) DarajaResult.Loading(isLoading = true)
    else DarajaResult.Loading(isLoading = false)
}

fun <T : Any> DarajaResult<T>.onSuccess(action: (value: T) -> Unit): DarajaResult.Success<T>? {
    return if (this is DarajaResult.Success) this
    else null
}

fun <T : Any> DarajaResult<T>.onFailure(action: (value: T) -> Unit): DarajaResult.Failure? {
    return if (this is DarajaResult.Failure) this
    else null
}
