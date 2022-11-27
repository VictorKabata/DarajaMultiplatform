package com.vickikbt.darajakmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform