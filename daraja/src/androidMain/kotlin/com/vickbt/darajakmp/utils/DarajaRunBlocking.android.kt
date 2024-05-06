package com.vickbt.darajakmp.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

actual fun <T> darajaRunBlocking(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): T = runBlocking(context, block)
