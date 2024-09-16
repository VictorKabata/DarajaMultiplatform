package com.vickbt.daraja.android.di

import com.vickbt.darajakmp.Daraja
import org.koin.dsl.module

val appModule = module {
    // Provide a single instance of Daraja
    single {
        Daraja.Builder()
            .setConsumerKey("consumer_key")
            .setConsumerSecret("consumer_secret")
            .setPassKey("pass_key")
            .isSandbox()
            .build()
    }
}