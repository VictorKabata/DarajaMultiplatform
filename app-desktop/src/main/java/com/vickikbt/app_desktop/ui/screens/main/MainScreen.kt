package ui.screens.main

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.vickikbt.app_android.ui.screens.HomeScreen
import com.vickikbt.app_android.ui.theme.DarajaKmpTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(applicationScope: ApplicationScope) {

    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Notflix",
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            width = Dp.Unspecified,
            height = Dp.Unspecified,
        )
    ) {

        DarajaKmpTheme (darkTheme = true) {
            Surface(color = MaterialTheme.colors.surface) {
                HomeScreen()
            }
        }
    }
}
