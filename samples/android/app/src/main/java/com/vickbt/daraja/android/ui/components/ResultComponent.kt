package com.vickbt.daraja.android.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultComponent(modifier: Modifier = Modifier, result: String, onClickCopy: (String) -> Unit) {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface.copy(alpha = .6f))
    ) {
        Column(modifier = modifier) {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.TopEnd),
                    onClick = { onClickCopy(result) }) {
                    Icon(
                        imageVector = Icons.Rounded.AddCircle,
                        contentDescription = "Copy",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                modifier = Modifier.padding(8.dp),
                text = result,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}