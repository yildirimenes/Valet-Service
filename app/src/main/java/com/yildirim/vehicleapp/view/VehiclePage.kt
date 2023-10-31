package com.yildirim.vehicleapp.view
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yildirim.vehicleapp.R
import com.yildirim.vehicleapp.components.CustomRow
import com.yildirim.vehicleapp.entity.Vehicles
import com.yildirim.vehicleapp.viewmodel.VehicleViewModel
import com.yildirim.vehicleapp.viewmodelfactory.VehicleViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclePage(getVehicles: Vehicles){
    val customerName = remember { mutableStateOf("") }
    val vehicleName = remember { mutableStateOf("") }
    val vehicleNumberPlate = remember { mutableStateOf("") }
    val vehicleLocationDescription = remember { mutableStateOf("") }

    val context = LocalContext.current
    val viewModel : VehicleViewModel = viewModel(
        factory = VehicleViewModelFactory(context.applicationContext as Application)
    )

    LaunchedEffect(key1 = true){
        customerName.value = getVehicles.customer_name
        vehicleName.value = getVehicles.vehicle_name
        vehicleNumberPlate.value = getVehicles.vehicle_number_plate
        vehicleLocationDescription.value = getVehicles.vehicle_location_description

    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.car_information)) })
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Card(modifier = Modifier
                .padding(all = 5.dp)
                .fillMaxWidth()
            ) {
                Column {
                    Spacer(modifier = Modifier.size(10.dp))
                    CustomRow(iconRes = R.drawable.baseline_person_24, text = getVehicles.customer_name)
                    CustomRow(iconRes = R.drawable.baseline_directions_car_24, text = getVehicles.vehicle_name)
                    CustomRow(iconRes = R.drawable.baseline_call_to_action_24, text = getVehicles.vehicle_number_plate)
                    CustomRow(iconRes = R.drawable.baseline_location_on_24, text = getVehicles.vehicle_location_description)
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }
    }
}


