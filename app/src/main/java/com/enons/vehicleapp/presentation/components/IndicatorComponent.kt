package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IndicatorComponent(
    pageSize:Int,
    currentPage:Int,
    selectedColor: Color = MaterialTheme.colorScheme.secondary,
    unSelectedColor: Color = MaterialTheme.colorScheme.secondaryContainer
){

    Row (
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(pageSize){
            Spacer(modifier = Modifier.size(2.5.dp))
            Box(
                modifier = Modifier
                    .height(14.dp)
                    .width(width = if (it == currentPage) 32.dp else 16.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = if (it == currentPage) selectedColor else unSelectedColor)
            )
        }
    }
}
