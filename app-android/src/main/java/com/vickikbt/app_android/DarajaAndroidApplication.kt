package com.vickikbt.app_android

import android.app.Application
import com.vickikbt.darajakmp.BuildConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class DarajaAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Napier.base(DebugAntilog(defaultTag = "App-Android"))
    }
}
