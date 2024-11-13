@file:OptIn(ExperimentalMaterial3Api::class)

package com.vickbt.daraja.android.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickbt.darajakmp.Daraja
import com.vickbt.darajakmp.network.models.DynamicQrResponse
import com.vickbt.darajakmp.utils.DarajaTransactionCode
import com.vickbt.darajakmp.utils.onFailure
import com.vickbt.darajakmp.utils.onSuccess
import java.util.UUID

@Composable
fun QrCodeScreen(modifier: Modifier, daraja: Daraja, onResult: (DynamicQrResponse) -> Unit = {}) {

    val context = LocalContext.current

    var merchantName by remember { mutableStateOf("Test Supermarket") }
    var amount by remember { mutableIntStateOf(1) }
    var cpi by remember { mutableStateOf("373132") }

    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically,
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = merchantName,
            onValueChange = { merchantName = it },
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            ),
            label = { Text(text = "Merchant Name") },
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            //keyboardActions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = cpi,
            onValueChange = { cpi = it },
            singleLine = true,
            maxLines = 1,
            textStyle =
            TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            ),
            label = { Text(text = "CPI") },
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            // keyboardActions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = amount.toString(),
            onValueChange = { amount = it.toInt() },
            singleLine = true,
            maxLines = 1,
            textStyle =
            TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            ),
            label = { Text(text = "CPI") },
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            // keyboardActions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Box(modifier = Modifier) {
            FloatingActionButton(
                modifier = Modifier.align(Alignment.Center),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                elevation = FloatingActionButtonDefaults.elevation(),
                onClick = {
                    isLoading = true

                    daraja.generateDynamicQr(
                        merchantName = merchantName,
                        cpi = cpi,
                        amount = amount,
                        referenceNumber = UUID.randomUUID().toString(),
                        transactionCode = DarajaTransactionCode.PB,
                        size = 300
                    ).onSuccess {
                        onResult(it)
                        isLoading = false
                    }.onFailure {
                        Toast.makeText(context, "Error: ${it.errorMessage}", Toast.LENGTH_SHORT)
                            .show()
                        isLoading = false
                    }

                },
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        trackColor = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Icons.AutoMirrored.Rounded.Send,
                        contentDescription = "Pay"
                    )
                }
            }
        }
    }

}