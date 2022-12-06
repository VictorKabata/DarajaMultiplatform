package com.vickikbt.darajakmp.utils

import com.vickikbt.darajakmp.network.models.DarajaException

sealed class DarajaResult<out T> {
    data class Success<out T : Any>(val data: T) : DarajaResult<T>()
    data class Failure(val exception: DarajaException) : DarajaResult<Nothing>()
    data class Loading<out T : Boolean>(val isLoading: T) : DarajaResult<T>()
}

inline fun <T : Any> DarajaResult<T>.getOrNull(): T? {
    return if (this is DarajaResult.Success) this.data
    else null
}

internal inline fun <T : Any> DarajaResult<T>.throwOnFailure(): Exception {
    return if (this is DarajaResult.Failure) this.exception
    else throw Exception()
}

internal inline fun <T : Any> DarajaResult<T>.getOrThrow(): T? {
    return if (this is DarajaResult.Success) this.data
    else throw this.throwOnFailure()
}

// ToDo: DarajaResult for loading state
fun <T : Any> DarajaResult<T>.isLoading(data: (isLoading: Boolean) -> Unit): DarajaResult<T> {
    return this
}

fun <T : Any> DarajaResult<T>.onSuccess(action: (value: T) -> Unit): DarajaResult<T>? {
    return if (this is DarajaResult.Success) this
    else null
}

fun <T : Any> DarajaResult<T>.onFailure(action: (exception: DarajaException) -> Unit): DarajaResult<T>? {
    return if (this is DarajaResult.Failure) this
    else null
}
