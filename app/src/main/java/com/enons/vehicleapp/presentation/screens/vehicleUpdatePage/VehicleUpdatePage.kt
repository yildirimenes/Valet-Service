package com.enons.vehicleapp.presentation.screens.vehicleUpdatePage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.vehicleapp.R
import com.enons.vehicleapp.presentation.components.CustomBtn
import com.enons.vehicleapp.presentation.components.PhoneField
import com.enons.vehicleapp.presentation.components.UpdateOutlinedTextField
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.UpdateOutlinedLocationTextField
import com.enons.vehicleapp.presentation.components.UpdateOutlinedNumberPlateTextField
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun VehicleUpdatePage(navController: NavController, getVehicles: Vehicles) {
    val localFocusManager = LocalFocusManager.current
    var tfCustomerName by remember { mutableStateOf("") }
    var tfCustomerPhoneNumber by remember { mutableStateOf("") }
    var tfVehicleName by remember { mutableStateOf("") }
    var tfVehicleNumberPlate by remember { mutableStateOf("") }
    var tfVehicleLocationDescription by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val viewModel: VehicleUpdateViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        tfCustomerName = getVehicles.customer_name
        tfCustomerPhoneNumber = getVehicles.customer_phone_number
        tfVehicleName = getVehicles.vehicle_name
        tfVehicleNumberPlate = getVehicles.vehicle_number_plate
        tfVehicleLocationDescription = getVehicles.vehicle_location_description
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.car_update)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)
            .imePadding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UpdateOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = tfCustomerName,
                onValueChange = { tfCustomerName = it },
                label = { Text(stringResource(id = R.string.customer_name)) },
            )

            Spacer(modifier = Modifier.size(20.dp))

            PhoneField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                phone = tfCustomerPhoneNumber,
                label = { Text(stringResource(id = R.string.customer_phone_number)) },
                onPhoneChanged = { tfCustomerPhoneNumber = it }
            )

            Spacer(modifier = Modifier.size(20.dp))

            UpdateOutlinedNumberPlateTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = tfVehicleNumberPlate,
                onValueChange = { tfVehicleNumberPlate = it.uppercase(Locale.ROOT) },
                label = { Text(stringResource(id = R.string.vehicle_number_plate)) },
            )

            Spacer(modifier = Modifier.size(20.dp))

            UpdateOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = tfVehicleName,
                onValueChange = { tfVehicleName = it },
                label = { Text(stringResource(id = R.string.vehicle_name)) },
            )

            Spacer(modifier = Modifier.size(20.dp))

            UpdateOutlinedLocationTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = tfVehicleLocationDescription,
                onValueChange = { tfVehicleLocationDescription = it },
                label = { Text(stringResource(id = R.string.vehicle_location_description)) },
            )

            Spacer(modifier = Modifier.size(20.dp))

            CustomBtn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                containerColor = colorResource(id = R.color.dark_green),
                contentColor = colorResource(id = R.color.color_3),
                onClick = {
                    viewModel.update(
                        vehicleId = getVehicles.vehicle_id,
                        customerName = tfCustomerName,
                        customerPhoneNumber = tfCustomerPhoneNumber,
                        vehicleName = tfVehicleName,
                        vehicleNumberPlate = tfVehicleNumberPlate,
                        vehicleLocationDescription = tfVehicleLocationDescription,
                        vehicleCheckInDate = getVehicles.vehicle_check_in_date,
                        vehicleCheckInHours = getVehicles.vehicle_check_in_hours
                    )
                    localFocusManager.clearFocus()
                    navController.navigate(Screen.HomePage.route)
                },
                text = stringResource(id = R.string.update)
            )

            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}
