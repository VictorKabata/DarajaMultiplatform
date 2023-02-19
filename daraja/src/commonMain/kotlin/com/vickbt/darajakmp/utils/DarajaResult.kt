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

package com.vickbt.darajakmp.utils

import com.vickbt.darajakmp.network.models.DarajaException
import kotlin.native.ObjCName

/**Encapsulate success result in with value of type [T]
 * or a failure result of type [DarajaException]*/
@ObjCName(swiftName = "DarajaResult")
sealed class DarajaResult<out T> {
    @ObjCName(swiftName = "Success")
    data class Success<out T : Any>(val data: T) : DarajaResult<T>()

    @ObjCName(swiftName = "Error")
    data class Failure(val exception: DarajaException) : DarajaResult<Nothing>()

    // object Loading : DarajaResult<Nothing>() ToDo
}

/**Returns result of type [T] on success or null on failure
 *
 * @receiver [DarajaResult]
 * */
internal fun <T : Any> DarajaResult<T>.getOrNull(): T? {
    return if (this is DarajaResult.Success) this.data
    else null
}

/**Returns exception of type [DarajaException] on failure
 *
 * @receiver [DarajaResult]
 * */
internal fun <T : Any> DarajaResult<T>.throwOnFailure(): DarajaException {
    return if (this is DarajaResult.Failure) this.exception
    else throw DarajaException()
}

/**Returns result of type [T] on success or exception of type [DarajaException] on failure
 *
 * @receiver [DarajaResult]
 * */
internal fun <T : Any> DarajaResult<T>.getOrThrow(): T {
    return if (this is DarajaResult.Success) this.data
    else throw this.throwOnFailure()
}

/* ToDo
inline fun <T : Any> DarajaResult<T>.isLoading(crossinline action: (isLoading: Boolean) -> Unit): DarajaResult<T> {
    if (this is DarajaResult.Loading) action(true) else action(false)
    return this
}*/

/**Returns result of type [T] on success
 *
 * @receiver [DarajaResult]
 * */
inline fun <T : Any> DarajaResult<T>.onSuccess(crossinline action: (T) -> Unit): DarajaResult<T> {
    if (this is DarajaResult.Success) action(this.data)
    return this
}

/**Returns exception of type [DarajaException] on failure
 *
 * @receiver [DarajaResult]
 * */
inline fun <T : Any> DarajaResult<T>.onFailure(crossinline action: (exception: DarajaException) -> Unit): DarajaResult<T> {
    if (this is DarajaResult.Failure) action(this.exception)
    return this
}
