package com.yildirim.vehicleapp.ui.components
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.size(250.dp, 50.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun CallButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = "Call", fontSize = 16.sp)
        }
    }
}

@Composable
fun MessageButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Row {
            Icon(
                imageVector = Icons.Default.MailOutline,
                contentDescription = null,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = " Message ", fontSize = 16.sp)
        }
    }
}
