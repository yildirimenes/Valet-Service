package com.enons.vehicleapp.presentation.components
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CombinedDropdownAndTextField(
    modifier: Modifier = Modifier,
    onSelectionChanged: (String) -> Unit,
    textFieldValue: String,
    onTextFieldValueChanged: (String) -> Unit,
    label: @Composable () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CityNumberDropdown(onSelectionChanged = onSelectionChanged)
        Spacer(modifier = Modifier.width(12.dp))
        RegisterOutlinedNumberPlateTextField(
            modifier = Modifier.weight(1f),
            value = textFieldValue,
            onValueChange = onTextFieldValueChanged,
            label = label
        )
    }
}
