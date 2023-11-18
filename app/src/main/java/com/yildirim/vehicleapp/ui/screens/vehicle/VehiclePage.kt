@file:Suppress("NAME_SHADOWING")
package com.yildirim.vehicleapp.ui.screens.vehicle
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.yildirim.vehicleapp.R
import com.yildirim.vehicleapp.ui.components.CallButton
import com.yildirim.vehicleapp.ui.components.CustomRow
import com.yildirim.vehicleapp.ui.components.MessageButton
import com.yildirim.vehicleapp.data.model.Vehicles
import com.yildirim.vehicleapp.ui.screens.vehicle.viewmodel.VehicleViewModel
import com.yildirim.vehicleapp.ui.screens.vehicle.viewmodel.VehicleViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclePage(navController: NavController ,getVehicles: Vehicles){
    val customerName = remember { mutableStateOf("") }
    val vehicleName = remember { mutableStateOf("") }
    val elapsedTime = remember { mutableStateOf("") }
    val valetFee = remember { mutableStateOf("") }
    val vehicleNumberPlate = remember { mutableStateOf("") }
    val vehicleLocationDescription = remember { mutableStateOf("") }
    //val buttonState = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val viewModel : VehicleViewModel = viewModel(
        factory = VehicleViewModelFactory(context.applicationContext as Application)
    )
    LaunchedEffect(key1 = true){
        //User Information
        customerName.value = getVehicles.customer_name
        vehicleName.value = getVehicles.vehicle_name
        vehicleNumberPlate.value = getVehicles.vehicle_number_plate
        vehicleLocationDescription.value = getVehicles.vehicle_location_description
        //Calculation Price Operations
        val startDateValue = getVehicles.vehicle_check_in_date
        val result = viewModel.calculateTimeDifference(startDateValue)
        elapsedTime.value = result.first
        valetFee.value = result.second.toString()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.car_information))
                },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .padding(all = 5.dp)
                    .fillMaxWidth(),

            ) {
                Column {
                    Spacer(modifier = Modifier.size(10.dp))
                    CustomRow(iconRes = R.drawable.baseline_person_24, text = getVehicles.customer_name)
                    CustomRow(iconRes = R.drawable.baseline_directions_car_24, text = getVehicles.vehicle_name)
                    CustomRow(iconRes = R.drawable.baseline_call_to_action_24, text = getVehicles.vehicle_number_plate)
                    CustomRow(iconRes = R.drawable.baseline_date_range_24, text = getVehicles.vehicle_check_in_date)
                    CustomRow(iconRes = R.drawable.baseline_location_on_24, text = getVehicles.vehicle_location_description)

                    Row(
                        modifier = Modifier
                            .padding(all = 2.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        CallButton(
                            onClick = {
                                viewModel.makePhoneCall(customerPhone = getVehicles.customer_phone_number,context)
                            }
                        )
                        MessageButton(
                            onClick = {
                                val customerName = getVehicles.customer_name
                                val vehicleNumberPlate = getVehicles.vehicle_number_plate
                                val vehicleLocationDescription = getVehicles.vehicle_location_description
                                val formattedPhoneNumber = "0${getVehicles.customer_phone_number}"
                                val currentHours = getVehicles.vehicle_check_in_hours

                                viewModel.sendMessage(context, customerName, vehicleNumberPlate, vehicleLocationDescription, formattedPhoneNumber,currentHours)
                            }
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .padding(all = 5.dp)
                    .fillMaxWidth(),
                ) {
                Row(
                    modifier = Modifier
                        .padding(all = 2.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Spacer(modifier = Modifier.size(10.dp))
                        CustomRow(iconRes = R.drawable.baseline_price_change_24, text = valetFee.value)
                        CustomRow(iconRes = R.drawable.baseline_access_time_filled_24, text = elapsedTime.value)
                    }
                    FloatingActionButton(
                        modifier = Modifier.padding(top = 25.dp, end =   10.dp),
                        onClick = {
                                  viewModel.msgBillButton(context,getVehicles.customer_name,getVehicles.customer_phone_number)
                        },
                        containerColor = colorResource(id = R.color.purple_200),
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_analytics_24),
                                contentDescription = "", tint = Color.White
                            )
                        }
                    )
                }
            }
        }
    }
}



