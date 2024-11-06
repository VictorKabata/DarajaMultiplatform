package com.vickbt.daraja.android.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vickbt.daraja.android.ui.components.CollapsableCard
import com.vickbt.darajakmp.Daraja
import org.koin.compose.koinInject

@Composable
fun MainScreen(modifier: Modifier = Modifier, daraja: Daraja = koinInject()) {
    LazyColumn(modifier = modifier.padding(horizontal = 12.dp)) {
        item {
            CollapsableCard(
                cardTitle = "M-Pesa Express",
                requestContent = { MpesaExpressScreen(modifier = Modifier, daraja = daraja) },
                responseContent = {}
            )
        }

        item {
            CollapsableCard(
                cardTitle = "Dynamic QR",
                requestContent = { QrCodeScreen(modifier = Modifier, daraja = daraja) },
                responseContent = {}
            )
        }

        item {
            CollapsableCard(
                cardTitle = "C2B Registration",
                requestContent = { C2BScreen(modifier = Modifier, daraja = daraja) },
                responseContent = {}
            )
        }

        item {
            CollapsableCard(
                cardTitle = "Initiate C2B",
                requestContent = { C2BInitiateScreen(modifier = Modifier, daraja = daraja) },
                responseContent = {}
            )
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {

}