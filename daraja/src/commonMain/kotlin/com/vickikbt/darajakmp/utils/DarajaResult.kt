package com.vickikbt.darajakmp.utils

import com.vickikbt.darajakmp.network.models.DarajaException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed class DarajaResult<out T> constructor(internal val value:Any?) {
    internal data class Success<out T : Any>(val data: T) : DarajaResult<T>(data)
    internal data class Failure(val exception: DarajaException) : DarajaResult<Nothing>(exception)
    internal data class Loading(val isLoading: Boolean) : DarajaResult<Boolean>(isLoading)
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

fun <T : Any> DarajaResult<T>.isLoading(action: (isLoading: Boolean) -> Unit): DarajaResult<T>? {
    return if (this is DarajaResult.Loading) this else null
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> DarajaResult<T>.onSuccess(action: (value: T) -> Unit): DarajaResult<T>? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    action(value as T)
    return if (this is DarajaResult.Success) this else null
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> DarajaResult<T>.onFailure(action: (exception: DarajaException) -> Unit): DarajaResult<T>? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    action(value as DarajaException)
    return if (this is DarajaResult.Failure) this else null
}
