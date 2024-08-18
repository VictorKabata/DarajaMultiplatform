@file:OptIn(ExperimentalMaterial3Api::class)

package com.vickbt.daraja.android.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Send
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickbt.daraja.android.ui.theme.DarajaAndroidTheme

@Composable
fun MpesaExpressScreen(modifier: Modifier = Modifier) {

    val tillNumber by remember { mutableStateOf("174379") }
    var amount by remember { mutableStateOf(1) }
    var phoneNumber by remember { mutableStateOf("") }

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
            value = amount.toString(),
            onValueChange = { amount = it.toInt() },
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            ),
            label = { Text(text = "Amount") },
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumber,
            onValueChange = { it.let { phoneNumber = it } },
            singleLine = true,
            maxLines = 1,
            textStyle =
            TextStyle(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            ),
            label = { Text(text = "Phone Number") },
            colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colorScheme.primary),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
        )

        FloatingActionButton(
            modifier = Modifier,
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            elevation = FloatingActionButtonDefaults.elevation(),
            onClick = {},
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.AutoMirrored.Rounded.Send,
                contentDescription = "Pay"
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun MpesaExpressScreenPreview() {
    DarajaAndroidTheme(darkTheme = true) {
        MpesaExpressScreen()
    }
}