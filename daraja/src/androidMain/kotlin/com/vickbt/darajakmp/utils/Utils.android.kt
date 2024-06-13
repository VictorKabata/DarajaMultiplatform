package com.vickbt.darajakmp.utils

import java.io.File

internal actual fun String.getFileFromPath(): String {
    return File(this).readText()
}
