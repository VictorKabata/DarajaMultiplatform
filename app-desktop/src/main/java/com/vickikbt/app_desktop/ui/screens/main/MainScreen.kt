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

package ui.screens.main

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.vickikbt.app_desktop.ui.screens.home.HomeScreen
import com.vickikbt.app_android.ui.theme.DarajaKmpTheme

@Composable
fun MainScreen(applicationScope: ApplicationScope) {
    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Daraja Multiplatform Desktop",
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = Dp.Unspecified,
            height = Dp.Unspecified
        )
    ) {
        DarajaKmpTheme(darkTheme = true) {
            Surface(color = MaterialTheme.colors.surface) {
                HomeScreen()
            }
        }
    }
}
