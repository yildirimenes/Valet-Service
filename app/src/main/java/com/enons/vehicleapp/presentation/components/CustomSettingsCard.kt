package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun CustomSettingsCard(iconRes: Int, text: String, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.color_3)

        ),
        modifier = Modifier
            .padding(all = 5.dp)
            .fillMaxSize()
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(all = 15.dp)) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = "",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.W300,
                color = colorResource(id = R.color.black)

            )
        }
    }
}
