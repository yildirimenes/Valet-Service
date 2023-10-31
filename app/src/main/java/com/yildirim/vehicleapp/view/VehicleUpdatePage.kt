package com.yildirim.vehicleapp.view
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.yildirim.vehicleapp.R
import com.yildirim.vehicleapp.components.CustomButton
import com.yildirim.vehicleapp.components.UpdateOutlinedTextField
import com.yildirim.vehicleapp.entity.Vehicles
import com.yildirim.vehicleapp.viewmodel.VehicleUpdateViewModel
import com.yildirim.vehicleapp.viewmodelfactory.VehicleUpdateViewModelFactory


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun VehicleUpdatePage(navController: NavController,getVehicles: Vehicles){
    val tfCustomerName = remember { mutableStateOf("") }
    val tfVehicleName = remember { mutableStateOf("") }
    val tfVehicleNumberPlate = remember { mutableStateOf("") }
    val tfVehicleLocationDescription = remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel : VehicleUpdateViewModel = viewModel(
        factory = VehicleUpdateViewModelFactory(context.applicationContext as Application)
    )
    val localFocusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true){
        tfCustomerName.value = getVehicles.customer_name
        tfVehicleName.value = getVehicles.vehicle_name
        tfVehicleNumberPlate.value = getVehicles.vehicle_number_plate
        tfVehicleLocationDescription.value = getVehicles.vehicle_location_description
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.car_update)) })
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UpdateOutlinedTextField(
                value = tfCustomerName.value,
                onValueChange = {tfCustomerName.value = it},
                label = {Text(stringResource(id = R.string.customer_name))},
            )
            Spacer(modifier = Modifier.size(30.dp))
            UpdateOutlinedTextField(
                value = tfVehicleName.value,
                onValueChange = {tfVehicleName.value = it},
                label = {Text(stringResource(id = R.string.vehicle_name))},
            )
            Spacer(modifier = Modifier.size(30.dp))
            UpdateOutlinedTextField(
                value = tfVehicleNumberPlate.value,
                onValueChange = {tfVehicleNumberPlate.value = it},
                label = {Text(stringResource(id = R.string.vehicle_number_plate))},
            )
            Spacer(modifier = Modifier.size(30.dp))
            UpdateOutlinedTextField(
                value = tfVehicleLocationDescription.value,
                onValueChange = {tfVehicleLocationDescription.value = it},
                label = {Text(stringResource(id = R.string.vehicle_location_description))},
            )
            Spacer(modifier = Modifier.size(30.dp))

            CustomButton(
                onClick = {
                    val customerName = tfCustomerName.value
                    val vehicleName = tfVehicleName.value
                    val vehicleNumberPlate = tfVehicleNumberPlate.value
                    val vehicleLocationDescription = tfVehicleLocationDescription.value

                    viewModel.update(getVehicles.vehicle_id,customerName,vehicleName,vehicleNumberPlate,vehicleLocationDescription)
                    localFocusManager.clearFocus()
                    navController.navigate("category_page")
                },
                text = stringResource(id = R.string.update)
            )

        }

    }

}