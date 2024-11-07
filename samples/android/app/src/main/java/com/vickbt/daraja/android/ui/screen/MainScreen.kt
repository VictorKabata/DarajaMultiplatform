package com.vickbt.daraja.android.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vickbt.daraja.android.ui.components.CollapsableCard
import com.vickbt.daraja.android.ui.components.ResultComponent
import com.vickbt.daraja.android.utils.toJson
import com.vickbt.darajakmp.Daraja
import org.koin.compose.koinInject

@Composable
fun MainScreen(modifier: Modifier = Modifier, daraja: Daraja = koinInject()) {
    LazyColumn(modifier = modifier.padding(horizontal = 12.dp)) {
        item {
            var requestContent by remember { mutableStateOf("") }
            Log.e("VicKbt", "Result: ${requestContent.toJson()}")
            CollapsableCard(
                cardTitle = "M-Pesa Express",
                requestContent = {
                    MpesaExpressScreen(
                        modifier = Modifier,
                        daraja = daraja,
                        onResult = { requestContent = it.toJson() })
                },
                responseContent = {
                    ResultComponent(
                        modifier = Modifier,
                        result = requestContent.toJson(),
                        onClickCopy = {}
                    )
                }
            )
        }

        item {
            var requestContent by remember { mutableStateOf("") }
            CollapsableCard(
                cardTitle = "Dynamic QR",
                requestContent = {
                    QrCodeScreen(
                        modifier = Modifier,
                        daraja = daraja,
                        onResult = { requestContent = it.toJson() })
                },
                responseContent = {
                    ResultComponent(
                        modifier = Modifier,
                        result = requestContent.toJson(),
                        onClickCopy = {}
                    )
                }
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