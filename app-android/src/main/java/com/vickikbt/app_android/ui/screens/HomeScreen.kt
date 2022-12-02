package com.vickikbt.app_android.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.vickikbt.app_android.ui.theme.DarajaKmpTheme

@Composable
fun HomeScreen() {
    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DarajaKmpTheme {
        HomeScreen()
    }
}
