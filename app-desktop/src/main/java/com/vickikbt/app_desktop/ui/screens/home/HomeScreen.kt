package com.vickikbt.app_android.ui.screens

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
import com.vickikbt.app_android.ui.theme.DarajaKmpTheme

@Composable
fun HomeScreen() {
    var tillNumber by remember { mutableStateOf("174379") }
    var amount by remember { mutableStateOf(1) }
    var phoneNumber by remember { mutableStateOf("0714091304") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 2.dp),
            text = "Daraja KMP Desktop",
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
                value = tillNumber,
                onValueChange = { tillNumber = it },
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.onBackground
                ),
                label = { Text(text = "Till Number") },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colors.primary),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )

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
                label = { Text(text = "Till Number") },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colors.primary),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
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
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            )
        }

        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                // ToDo:
                /*viewModel.initiateMpesaPayment(
                    businessShortCode = tillNumber,
                    amount = amount,
                    phoneNumber = phoneNumber,
                    transactionType = DarajaTransactionType.CustomerBuyGoodsOnline,
                    transactionDesc = "Mpesa payment",
                    callbackUrl = "https://mydomain.com/path",
                    accountReference = "Daraja KMP Android"
                )*/
            }
        ) {
            Text(text = "Make Payment", fontSize = 20.sp)
        }
    }

}

@Preview
@Composable
fun DefaultPreview() {
    DarajaKmpTheme {
        HomeScreen()
    }
}
