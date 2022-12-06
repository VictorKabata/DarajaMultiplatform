package com.vickikbt.app_android.di

import com.vickikbt.app_android.ui.screens.home.HomeViewModel
import com.vickikbt.darajakmp.Daraja
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {

    single {
        Daraja.Builder()
            .setConsumerKey("NrF3UW9YCIUeTeLeamBC9HRjlaGkw6RZ")
            .setConsumerSecret("lARzdAdZaRAtrXZ0")
            .setPassKey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919")
            .build()
    }

    viewModelOf(::HomeViewModel)
}
