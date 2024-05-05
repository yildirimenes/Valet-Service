package com.enons.vehicleapp.presentation.screens.register_vehicle

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.enons.vehicleapp.R
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.CarNameDropdown
import com.enons.vehicleapp.presentation.components.CombinedDropdownAndTextField
import com.enons.vehicleapp.presentation.components.CustomButton
import com.enons.vehicleapp.presentation.components.PhoneField
import com.enons.vehicleapp.presentation.components.RegisterOutlinedTextField
import com.enons.vehicleapp.presentation.screens.register_vehicle.viewmodel.VehicleRegisterViewModel
import com.enons.vehicleapp.presentation.screens.register_vehicle.viewmodel.VehicleRegisterViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun VehicleRegisterPage(navController: NavController) {
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current
    var tfCustomerName by remember { mutableStateOf("") }
    var tfCustomerPhoneNumber by remember { mutableStateOf("") }
    var tfVehicleLocationDescription by remember { mutableStateOf("") }
    var selectedBrand by remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }
    var selectedNumber by remember { mutableStateOf("34") }
    var textFieldValue by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val viewModel: VehicleRegisterViewModel = viewModel(
        factory = VehicleRegisterViewModelFactory(context.applicationContext as Application)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.car_register))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },

        ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            RegisterOutlinedTextField(
                value = tfCustomerName,
                onValueChange = { tfCustomerName = it },
                label = { Text(text = stringResource(id = R.string.customer_name)) }
            )
            Spacer(modifier = Modifier.size(30.dp))
            PhoneField(tfCustomerPhoneNumber,
                mask = "(000) 000 00 00",
                maskNumber = '0',
                label = { Text(text = stringResource(id = R.string.customer_phone_number)) },
                onPhoneChanged = { tfCustomerPhoneNumber = it }
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfVehicleLocationDescription,
                onValueChange = { tfVehicleLocationDescription = it },
                label = { Text(text = stringResource(id = R.string.vehicle_location_description)) }
            )
            Spacer(modifier = Modifier.size(20.dp))
            CombinedDropdownAndTextField(
                selectedNumber = selectedNumber,
                onSelectionChanged = {
                    selectedNumber = it
                },
                textFieldValue = textFieldValue,
                onTextFieldValueChanged = {
                    textFieldValue = it
                },
                label = { Text(text = stringResource(id = R.string.vehicle_number_plate))}
            )
            Spacer(modifier = Modifier.size(20.dp))
            CarNameDropdown { brand, model ->
                selectedBrand = brand
                selectedModel = model
            }
            Spacer(modifier = Modifier.size(20.dp))
            CustomButton(
                onClick = {
                    val customerName = tfCustomerName
                    val customerPhoneNumber = tfCustomerPhoneNumber
                    val vehicleName = "$selectedBrand $selectedModel"
                    val vehicleNumberPlate = "$selectedNumber $textFieldValue"
                    val vehicleLocationDescription = tfVehicleLocationDescription
                    val vehicleCheckInDate = viewModel.currentDate()
                    val vehicleCheckInHours = viewModel.currentTime()

                    if (customerName.isNotEmpty() && vehicleName.isNotEmpty() && vehicleNumberPlate.isNotEmpty() && vehicleLocationDescription.isNotEmpty()) {
                        viewModel.register(
                            customerName,
                            customerPhoneNumber,
                            vehicleName,
                            vehicleNumberPlate,
                            vehicleLocationDescription,
                            vehicleCheckInDate,
                            vehicleCheckInHours
                        )
                        localFocusManager.clearFocus()
                        navController.navigate(Screen.CategoryPage.route)
                    } else {
                        Toast.makeText(context, "Invalid Information", Toast.LENGTH_SHORT).show()
                    }
                },
                text = stringResource(id = R.string.register)
            )
        }
    }
}

