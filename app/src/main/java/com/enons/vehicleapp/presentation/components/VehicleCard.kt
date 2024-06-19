package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enons.vehicleapp.R
import com.enons.vehicleapp.data.local.model.Vehicles

@Composable
fun VehicleCard(
    vehicle: Vehicles,
    onItemClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .padding(all = 5.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.color_3)
        )
    ) {
        Column(
            modifier = Modifier.clickable(onClick = onItemClick)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(48.dp),
                    onClick = onEditClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_edit_24),
                        contentDescription = "Edit Icon",
                        tint = Color.LightGray
                    )
                }
                Image(
                    modifier = Modifier
                        .shadow(15.dp, shape = CircleShape)
                        .size(72.dp),
                    painter = painterResource(id = R.drawable.default_image),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = vehicle.vehicle_number_plate,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W300,
                    color = colorResource(id = R.color.black)
                )
                Spacer(modifier = Modifier.size(15.dp))
            }
        }
    }
}

