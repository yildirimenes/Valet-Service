package com.enons.vehicleapp.presentation.components
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.enons.vehicleapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CityNumberDropdown(onSelectionChanged: (String) -> Unit) {
    var dropdownControl by remember { mutableStateOf(false) }
    var selectedNumber by remember { mutableIntStateOf(34) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedCard(modifier = Modifier.padding(top = 5.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(60.dp)
                    .height(55.dp)
                    .clickable {
                        dropdownControl = true
                    }
            ) {
                Text(text = selectedNumber.toString())
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = ""
                )
            }

            DropdownMenu(
                expanded = dropdownControl,
                onDismissRequest = { dropdownControl = false },
                modifier = Modifier.requiredSizeIn(maxHeight = 300.dp)
            ) {
                for (i in 1..81) {
                    DropdownMenuItem(
                        text = { Text(text = i.toString()) },
                        onClick = {
                            dropdownControl = false
                            selectedNumber = i
                            onSelectionChanged(selectedNumber.toString())
                        },
                    )
                }
            }
        }
    }
}
