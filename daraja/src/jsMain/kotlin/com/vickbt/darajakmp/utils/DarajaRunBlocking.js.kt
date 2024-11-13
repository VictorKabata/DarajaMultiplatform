@file:OptIn(DelicateCoroutinesApi::class)

package com.vickbt.darajakmp.utils

import kotlinx.browser.window
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal actual fun <T> darajaRunBlocking(
    block: suspend () -> T
): T {
    var result: T? = null
    var exception: Throwable? = null

    val job = GlobalScope.launch(Dispatchers.Default) {
        try {
            result = block()
        } catch (e: Throwable) {
            exception = e
        }
    }

    while (true) {
        if (job.isCompleted) {
            exception?.let { throw it }
            return result as T
        }

        window.setTimeout({ /* empty */ }, 0)
    }
}