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

package com.vickbt.app_android.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vickbt.app_android.ui.theme.DarajaKmpTheme
import com.vickbt.darajakmp.utils.onFailure
import com.vickbt.darajakmp.utils.onSuccess
import com.vickikbt.app_android.R
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = get()) {
    val tillNumber by remember { mutableStateOf("174379") }
    var amount by remember { mutableStateOf(1) }
    var phoneNumber by remember { mutableStateOf("") }

    val mpesaResponse = viewModel.mpesaResponse.collectAsState().value

    val context = LocalContext.current

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (card, button) = createRefs()

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.35f)
                .padding(horizontal = 24.dp)
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            shape = RoundedCornerShape(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(
                    space = 24.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = amount.toString(),
                    onValueChange = { amount = it.toInt() },
                    singleLine = true,
                    maxLines = 1,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    label = { Text(text = "Amount") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = phoneNumber,
                    onValueChange = { it.let { phoneNumber = it } },
                    singleLine = true,
                    maxLines = 1,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    label = { Text(text = "Phone Number") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .size(64.dp)
                .constrainAs(button) {
                    top.linkTo(card.bottom)
                    bottom.linkTo(card.bottom)
                    start.linkTo(card.start)
                    end.linkTo(card.end)
                },
            shape = CircleShape,
            containerColor = colorResource(id = R.color.theme_color),
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(),
            onClick = {
                viewModel.initiateMpesaPayment(
                    businessShortCode = tillNumber,
                    amount = amount,
                    phoneNumber = phoneNumber,
                    transactionDesc = "Mpesa payment",
                    callbackUrl = "https://mydomain.com/path",
                    accountReference = "Daraja KMP Android"
                )
            }
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(id = R.drawable.ic_payment),
                contentDescription = "Pay"
            )
        }
    }

    mpesaResponse?.onSuccess {
        Toast.makeText(context, "Success: $it", Toast.LENGTH_SHORT).show()
    }?.onFailure {
        Toast.makeText(context, "Error: ${it.errorMessage}", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DarajaKmpTheme {
        HomeScreen()
    }
}
