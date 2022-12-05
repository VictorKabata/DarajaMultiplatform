package com.vickikbt.darajakmp.utils

fun <T> Result<T>.isLoading():Boolean {
    val some=this.onSuccess {  }
    return this.isSuccess && this.getOrDefault("true")=="true"
}
