package com.vickbt.darajakmp.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

expect fun <T> darajaRunBlocking(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend CoroutineScope.() -> T
): T
