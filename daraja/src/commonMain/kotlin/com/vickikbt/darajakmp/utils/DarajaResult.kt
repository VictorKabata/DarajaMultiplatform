package com.vickikbt.darajakmp.utils

import com.vickikbt.darajakmp.network.models.DarajaException

sealed class DarajaResult<out T> {
    data class Success<out T : Any>(val data: T) : DarajaResult<T>()
    data class Failure(val exception: DarajaException) : DarajaResult<Nothing>()
    data class Loading(val isLoading: Boolean) : DarajaResult<Boolean>()
}

internal inline fun <T : Any> DarajaResult<T>.getOrNull(): T? {
    return if (this is DarajaResult.Success) this.data
    else null
}

internal inline fun <T : Any> DarajaResult<T>.throwOnFailure(): Exception {
    return if (this is DarajaResult.Failure) this.exception
    else throw Exception()
}

internal inline fun <T : Any> DarajaResult<T>.getOrThrow(): T {
    return if (this is DarajaResult.Success) this.data
    else throw this.throwOnFailure()
}

inline fun <T : Any> DarajaResult<T>.isLoading(crossinline action: (isLoading: Boolean) -> Unit): DarajaResult<T> {
    if (this is DarajaResult.Loading) action(true) else action(false)
    return this
}

inline fun <T : Any> DarajaResult<T>.onSuccess(crossinline action: (T) -> Unit): DarajaResult<T> {
    if (this is DarajaResult.Success) action(this.data)
    return this
}

inline fun <T : Any> DarajaResult<T>.onFailure(crossinline action: (exception: DarajaException) -> Unit): DarajaResult<T> {
    if (this is DarajaResult.Failure) action(this.exception)
    return this
}
