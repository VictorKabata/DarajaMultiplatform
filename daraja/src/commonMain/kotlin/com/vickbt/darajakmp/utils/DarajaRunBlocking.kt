package com.vickbt.darajakmp.utils

internal expect fun <T> darajaRunBlocking(block: suspend () -> T): T