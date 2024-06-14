package com.vickbt.darajakmp.utils

import com.vickbt.darajakmp.network.models.DarajaException
import platform.Foundation.NSBundle

internal actual fun String.getFileFromPath(): String {
    return NSBundle.mainBundle.pathForResource(name = this, ofType = null) ?: throw DarajaException(
        errorMessage = "Invalid file"
    )
}
