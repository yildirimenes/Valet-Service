package com.enons.vehicleapp.presentation.screens.vehicleUpdatePage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.enons.vehicleapp.presentation.screens.vehicleUpdatePage.viewmodel.VehicleUpdateViewModel
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
                title = {
                    Text(text = stringResource(id = R.string.car_update))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                value = tfCustomerName,
                onValueChange = { tfCustomerName = it },
                label = { Text(stringResource(id = R.string.customer_name)) },
            )
            Spacer(modifier = Modifier.size(20.dp))
            PhoneField(
                phone = tfCustomerPhoneNumber,
                label = { Text(stringResource(id = R.string.customer_phone_number)) },
                onPhoneChanged = { tfCustomerPhoneNumber = it }
            )
            Spacer(modifier = Modifier.size(20.dp))
            UpdateOutlinedNumberPlateTextField(
                value = tfVehicleNumberPlate,
                onValueChange = { tfVehicleNumberPlate = it.uppercase(Locale.ROOT) },
                label = { Text(stringResource(id = R.string.vehicle_number_plate)) },
            )
            Spacer(modifier = Modifier.size(20.dp))
            UpdateOutlinedTextField(
                value = tfVehicleName,
                onValueChange = { tfVehicleName = it },
                label = { Text(stringResource(id = R.string.vehicle_name)) },
            )
            Spacer(modifier = Modifier.size(20.dp))
            UpdateOutlinedLocationTextField(
                value = tfVehicleLocationDescription,
                onValueChange = { tfVehicleLocationDescription = it },
                label = { Text(stringResource(id = R.string.vehicle_location_description)) },
            )
            Spacer(modifier = Modifier.size(20.dp))
            CustomBtn(
                modifier = Modifier.fillMaxWidth(0.7f),
                containerColor = colorResource(id = R.color.dark_green),
                contentColor = colorResource(id = R.color.color_3),
                onClick = {
                    val customerName = tfCustomerName
                    val customerPhoneNumber = tfCustomerPhoneNumber
                    val vehicleName = tfVehicleName
                    val vehicleNumberPlate = tfVehicleNumberPlate
                    val vehicleLocationDescription = tfVehicleLocationDescription
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
                    navController.navigate(Screen.HomePage.route)
                },
                text = stringResource(id = R.string.update),
            )
            Spacer(modifier = Modifier.size(30.dp))

        }

    }

}