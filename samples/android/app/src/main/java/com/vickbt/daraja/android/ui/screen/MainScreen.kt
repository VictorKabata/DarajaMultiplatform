package com.vickbt.daraja.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vickbt.daraja.android.ui.components.CollapsableCard
import com.vickbt.darajakmp.Daraja
import org.koin.compose.koinInject

@Composable
fun MainScreen(modifier: Modifier = Modifier, daraja: Daraja = koinInject()) {
    Column(modifier = modifier.padding(horizontal = 12.dp)) {
        CollapsableCard(cardTitle = "M-Pesa Express") {
            MpesaExpressScreen(modifier = Modifier, daraja = daraja)
        }

        CollapsableCard(cardTitle = "Dynamic QR") {
            QrCodeScreen(modifier = Modifier, daraja = daraja)
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {

}