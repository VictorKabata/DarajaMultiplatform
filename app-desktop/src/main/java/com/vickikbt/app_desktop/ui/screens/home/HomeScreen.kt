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

package com.vickikbt.app_desktop.ui.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickbt.darajakmp.Daraja
import com.vickbt.darajakmp.utils.onFailure
import com.vickbt.darajakmp.utils.onSuccess
import com.vickikbt.app_android.ui.theme.DarajaKmpTheme

@Composable
fun HomeScreen() {
    val tillNumber by remember { mutableStateOf("174379") }
    var amount by remember { mutableStateOf(1) }
    var phoneNumber by remember { mutableStateOf("0714091304") }

    val daraja by remember {
        mutableStateOf(
            Daraja.Builder()
                .setConsumerKey("zg1m1CbMGx8E2BqVThHIJHFMWSnVJ4XA")
                .setConsumerSecret("z4CAY2TUw6rprEvy")
                .setPassKey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919")
                .isSandbox()
                .build()
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 2.dp),
            text = "Daraja Multiplatform Desktop",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 42.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(.8f),
                value = amount.toString(),
                onValueChange = { amount = it.toInt() },
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground
                ),
                label = { Text(text = "Amount") },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colors.primary),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(.8f),
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground
                ),
                label = { Text(text = "Phone Number") },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colors.primary),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
            )
        }

        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = { initiateMpesaStk(daraja, tillNumber, amount, phoneNumber) }
        ) {
            Text(text = "Make Payment", fontSize = 20.sp)
        }
    }
}

fun initiateMpesaStk(daraja: Daraja, tillNumber: String, amount: Int, phoneNumber: String) {
    daraja.initiateMpesaExpressPayment(
        businessShortCode = tillNumber,
        amount = amount,
        phoneNumber = phoneNumber,
        transactionDesc = "Mpesa payment",
        callbackUrl = "https://mydomain.com/path",
        accountReference = "Daraja KMP Android"
    ).onSuccess {
        println(message = "On success block called: $it")
    }.onFailure {
        println(message = "On failure block called: $it")
    }
}

@Preview
@Composable
fun DefaultPreview() {
    DarajaKmpTheme {
        HomeScreen()
    }
}
