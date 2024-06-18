package com.enons.vehicleapp.presentation.components
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CombinedDropdownAndTextField(
    onSelectionChanged: (String) -> Unit,
    textFieldValue: String,
    onTextFieldValueChanged: (String) -> Unit,
    label: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CityNumberDropdown(onSelectionChanged = onSelectionChanged)
        Spacer(modifier = Modifier.size(20.dp))
        RegisterOutlinedNumberPlateTextField(
            value = textFieldValue,
            onValueChange = onTextFieldValueChanged,
            label = label
        )
    }
}
