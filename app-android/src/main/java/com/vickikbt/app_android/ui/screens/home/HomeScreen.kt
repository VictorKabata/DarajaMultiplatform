package com.vickikbt.app_android.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.app_android.ui.screens.home.HomeViewModel
import com.vickikbt.app_android.ui.theme.DarajaKmpTheme
import com.vickikbt.darajakmp.utils.DarajaTransactionType
import com.vickikbt.darajakmp.utils.isLoading
import com.vickikbt.darajakmp.utils.onFailure
import com.vickikbt.darajakmp.utils.onSuccess
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = get()) {
    var tillNumber by remember { mutableStateOf("174379") }
    var amount by remember { mutableStateOf(1) }
    var phoneNumber by remember { mutableStateOf("0714091304") }

    val mpesaResponse = viewModel.mpesaResponse.collectAsState().value

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(horizontal = 2.dp),
            text = "Daraja KMP Android",
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
                    color = MaterialTheme.colorScheme.onBackground
                ),
                label = { Text(text = "Till Number") },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
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
                    color = MaterialTheme.colorScheme.onBackground
                ),
                label = { Text(text = "Till Number") },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
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
                    color = MaterialTheme.colorScheme.onBackground
                ),
                label = { Text(text = "Phone Number") },
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            )
        }

        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                viewModel.initiateMpesaPayment(
                    businessShortCode = tillNumber,
                    amount = amount,
                    phoneNumber = phoneNumber,
                    transactionType = DarajaTransactionType.CustomerBuyGoodsOnline,
                    transactionDesc = "Mpesa payment",
                    callbackUrl = "https://mydomain.com/path",
                    accountReference = "Daraja KMP Android"
                )
            }
        ) {
            Text(text = "Make Payment", fontSize = 20.sp)
        }
    }

    Log.i("Mpesa Response", "$mpesaResponse")

    mpesaResponse?.isLoading {
        Log.i("LOADING", "$it")
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
    }?.onSuccess {
        Log.i("SUCCESS", "$it")
        Toast.makeText(context, "Success: $it", Toast.LENGTH_SHORT).show()
    }?.onFailure {
        Log.i("ERROR", "Daraja Error: $it")
        Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DarajaKmpTheme {
        HomeScreen()
    }
}
