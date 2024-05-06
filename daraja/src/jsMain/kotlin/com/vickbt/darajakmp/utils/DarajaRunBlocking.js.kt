package com.vickbt.darajakmp.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.coroutines.CoroutineContext


actual fun <T> darajaRunBlocking(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> T
): dynamic = GlobalScope.promise(context) { block() }
