package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enons.vehicleapp.R


@Composable
fun CustomRow(iconRes: Int, text: String) {
    Row(
        modifier = Modifier.padding(all = 10.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = ""
        )
        Text(
            text = " : $text",
            fontSize = 20.sp,
            fontWeight = FontWeight.W300,
            color = colorResource(id = R.color.black)
        )
    }
}


