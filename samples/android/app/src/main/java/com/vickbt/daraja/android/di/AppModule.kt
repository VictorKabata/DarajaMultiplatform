package com.vickbt.daraja.android.di

import com.vickbt.darajakmp.Daraja
import org.koin.dsl.module

val appModule = module {
    // Provide a single instance of Daraja
    single {
        Daraja.Builder()
            .setConsumerKey("ewP4be7L00bA8RylBT0z9tEhlgkMlLJiLV0gJB374BeGnyJ7")
            .setConsumerSecret("o2fNGnTmYfHAfl65HAaK9jLh2a703wkTgiz1dqGCO9Vi3yBCOBL5Rpuu13kIgrQm")
            .setPassKey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919")
            .isSandbox()
            .build()
    }
}