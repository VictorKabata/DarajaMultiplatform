package com.vickbt.daraja.android

import android.app.Application
import com.vickbt.daraja.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DarajaAndroidApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DarajaAndroidApplication)
            modules(appModule)
        }

    }

}