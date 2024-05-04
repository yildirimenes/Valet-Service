package com.enons.vehicleapp.presentation.screens.update_vehicle

import android.annotation.SuppressLint
import android.app.Application
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.enons.vehicleapp.presentation.components.CustomButton
import com.enons.vehicleapp.presentation.components.PhoneField
import com.enons.vehicleapp.presentation.components.UpdateOutlinedTextField
import com.enons.vehicleapp.data.model.Vehicles
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.UpdateOutlinedLocationTextField
import com.enons.vehicleapp.presentation.components.UpdateOutlinedNumberPlateTextField
import com.enons.vehicleapp.presentation.screens.update_vehicle.viewmodel.VehicleUpdateViewModel
import com.enons.vehicleapp.presentation.screens.update_vehicle.viewmodel.VehicleUpdateViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun VehicleUpdatePage(navController: NavController, getVehicles: Vehicles) {
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current
    val tfCustomerName = remember { mutableStateOf("") }
    val tfCustomerPhoneNumber = remember { mutableStateOf("") }
    val tfVehicleName = remember { mutableStateOf("") }
    val tfVehicleNumberPlate = remember { mutableStateOf("") }
    val tfVehicleLocationDescription = remember { mutableStateOf("") }
    val viewModel: VehicleUpdateViewModel = viewModel(
        factory = VehicleUpdateViewModelFactory(context.applicationContext as Application)
    )
    LaunchedEffect(key1 = true) {
        tfCustomerName.value = getVehicles.customer_name
        tfCustomerPhoneNumber.value = getVehicles.customer_phone_number
        tfVehicleName.value = getVehicles.vehicle_name
        tfVehicleNumberPlate.value = getVehicles.vehicle_number_plate
        tfVehicleLocationDescription.value = getVehicles.vehicle_location_description
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.car_update))
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
                .verticalScroll(rememberScrollState())
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UpdateOutlinedTextField(
                value = tfCustomerName.value,
                onValueChange = { tfCustomerName.value = it },
                label = { Text(stringResource(id = R.string.customer_name)) },
            )
            Spacer(modifier = Modifier.size(30.dp))
            PhoneField(
                phone = tfCustomerPhoneNumber.value,
                label = { Text(stringResource(id = R.string.customer_phone_number)) },
                onPhoneChanged = { tfCustomerPhoneNumber.value = it }
            )
            Spacer(modifier = Modifier.size(30.dp))
            UpdateOutlinedNumberPlateTextField(
                value = tfVehicleNumberPlate.value,
                onValueChange = { tfVehicleNumberPlate.value = it },
                label = { Text(stringResource(id = R.string.vehicle_number_plate)) },
            )
            Spacer(modifier = Modifier.size(30.dp))
            UpdateOutlinedTextField(
                value = tfVehicleName.value,
                onValueChange = { tfVehicleName.value = it },
                label = { Text(stringResource(id = R.string.vehicle_name)) },
            )
            Spacer(modifier = Modifier.size(30.dp))
            UpdateOutlinedLocationTextField(
                value = tfVehicleLocationDescription.value,
                onValueChange = { tfVehicleLocationDescription.value = it },
                label = { Text(stringResource(id = R.string.vehicle_location_description)) },
            )
            Spacer(modifier = Modifier.size(30.dp))
            CustomButton(
                onClick = {
                    val customerName = tfCustomerName.value
                    val customerPhoneNumber = tfCustomerPhoneNumber.value
                    val vehicleName = tfVehicleName.value
                    val vehicleNumberPlate = tfVehicleNumberPlate.value
                    val vehicleLocationDescription = tfVehicleLocationDescription.value
                    val vehicleCheckInDate = getVehicles.vehicle_check_in_date
                    val vehicleCheckInHours = getVehicles.vehicle_check_in_hours

                    viewModel.update(
                        getVehicles.vehicle_id,
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
                },
                text = stringResource(id = R.string.update)
            )

        }

    }

}