package com.enons.vehicleapp.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enons.vehicleapp.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterForm(
    tfCustomerName: String,
    tfCustomerPhoneNumber: String,
    tfVehicleLocationDescription: String,
    selectedNumber: String,
    textFieldValue: String,
    onCustomerNameChanged: (String) -> Unit,
    onCustomerPhoneNumberChanged: (String) -> Unit,
    onVehicleLocationDescriptionChanged: (String) -> Unit,
    onSelectedBrandChanged: (String) -> Unit,
    onSelectedModelChanged: (String) -> Unit,
    onSelectedNumberChanged: (String) -> Unit,
    onTextFieldValueChanged: (String) -> Unit
) {
    RegisterOutlinedTextField(
        value = tfCustomerName,
        onValueChange = onCustomerNameChanged,
        label = { Text(text = stringResource(id = R.string.customer_name)) }
    )
    Spacer(modifier = Modifier.size(20.dp))
    PhoneField(
        phone = tfCustomerPhoneNumber,
        mask = "(000) 000 00 00",
        maskNumber = '0',
        label = { Text(text = stringResource(id = R.string.customer_phone_number)) },
        onPhoneChanged = onCustomerPhoneNumberChanged
    )
    Spacer(modifier = Modifier.size(20.dp))
    RegisterOutlinedTextField(
        value = tfVehicleLocationDescription,
        onValueChange = onVehicleLocationDescriptionChanged,
        label = { Text(text = stringResource(id = R.string.vehicle_location_description)) }
    )
    Spacer(modifier = Modifier.size(20.dp))
    CombinedDropdownAndTextField(
        onSelectionChanged = onSelectedNumberChanged,
        textFieldValue = textFieldValue,
        onTextFieldValueChanged = onTextFieldValueChanged,
        label = { Text(text = stringResource(id = R.string.vehicle_number_plate)) }
    )
    Spacer(modifier = Modifier.size(20.dp))
    CarNameDropdown { selectedBrand, selectedModel ->
        onSelectedBrandChanged(selectedBrand)
        onSelectedModelChanged(selectedModel)
    }
}
