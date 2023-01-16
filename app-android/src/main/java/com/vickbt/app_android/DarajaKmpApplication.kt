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

package com.vickbt.app_android

import android.app.Application
import com.vickbt.app_android.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DarajaKmpApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(presentationModule)
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(androidContext = this@DarajaKmpApplication)
            modules(appModules)
        }
    }
}
