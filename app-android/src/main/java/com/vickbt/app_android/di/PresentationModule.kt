/*
 * Copyright 2022 Daraja Multiplatform
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vickbt.app_android.di

import com.vickbt.app_android.ui.screens.home.HomeViewModel
import com.vickbt.darajakmp.Daraja
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {

    single {
        Daraja.Builder()
            .setConsumerKey("zg1m1CbMGx8E2BqVThHIJHFMWSnVJ4XA")
            .setConsumerSecret("z4CAY2TUw6rprEvy")
            .setPassKey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919")
            .isSandbox()
            .build()
    }

    viewModelOf(::HomeViewModel)
}
