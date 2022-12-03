package com.vickikbt.app_desktop

import androidx.compose.ui.window.application
import ui.screens.main.MainScreen

fun main() {
    return application {
        MainScreen(applicationScope = this)
    }
}
