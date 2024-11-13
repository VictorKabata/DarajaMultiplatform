package com.vickbt.darajakmp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.runBlocking

internal actual fun <T> darajaRunBlocking(block: suspend () -> T): T =
    runBlocking(context = Dispatchers.IO) { block() }