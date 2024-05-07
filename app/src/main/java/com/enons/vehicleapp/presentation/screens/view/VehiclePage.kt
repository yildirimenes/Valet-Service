@file:Suppress("NAME_SHADOWING")
package com.enons.vehicleapp.presentation.screens.view
import com.enons.vehicleapp.presentation.components.FlipCard
import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.vehicleapp.R
import com.enons.vehicleapp.presentation.components.CallButton
import com.enons.vehicleapp.presentation.components.CustomRow
import com.enons.vehicleapp.presentation.components.MessageButton
import com.enons.vehicleapp.data.model.Vehicles
import com.enons.vehicleapp.presentation.screens.viewmodel.VehiclePageViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiclePage(navController: NavController, getVehicles: Vehicles) {
    val context = LocalContext.current
    var customerName by remember { mutableStateOf("") }
    var vehicleName by remember { mutableStateOf("") }
    var vehicleNumberPlate by remember { mutableStateOf("") }
    var vehicleLocationDescription by remember { mutableStateOf("") }
    var elapsedTime by remember { mutableStateOf("") }
    var valetFee by remember { mutableStateOf("") }
    var hourly1 by remember { mutableStateOf("") }
    var hourly2 by remember { mutableStateOf("") }
    var hourly3 by remember { mutableStateOf("") }
    var hourly4 by remember { mutableStateOf("") }
    var hourly5 by remember { mutableStateOf("") }
    var daily by remember { mutableStateOf("") }
    val viewModel: VehiclePageViewModel = hiltViewModel()
    val hourlyFeeList = viewModel.hourlyFeeList.observeAsState(listOf())
    LaunchedEffect(key1 = true) {
        viewModel.load()
        //User Information
        customerName = getVehicles.customer_name
        vehicleName = getVehicles.vehicle_name
        vehicleNumberPlate = getVehicles.vehicle_number_plate
        vehicleLocationDescription = getVehicles.vehicle_location_description
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.car_information))
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .padding(all = 5.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.color_3)
                )
            ) {
                Column {
                    Spacer(modifier = Modifier.size(10.dp))
                    CustomRow(
                        iconRes = R.drawable.baseline_person_24,
                        text = getVehicles.customer_name
                    )
                    CustomRow(
                        iconRes = R.drawable.baseline_directions_car_24,
                        text = getVehicles.vehicle_name
                    )
                    CustomRow(
                        iconRes = R.drawable.baseline_call_to_action_24,
                        text = getVehicles.vehicle_number_plate
                    )
                    CustomRow(
                        iconRes = R.drawable.baseline_date_range_24,
                        text = getVehicles.vehicle_check_in_date
                    )
                    CustomRow(
                        iconRes = R.drawable.baseline_location_on_24,
                        text = getVehicles.vehicle_location_description
                    )

                    Row(
                        modifier = Modifier
                            .padding(all = 2.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CallButton(
                            onClick = {
                                viewModel.makePhoneCall(
                                    customerPhone = getVehicles.customer_phone_number,
                                    context
                                )
                            }
                        )
                        MessageButton(
                            onClick = {
                                val customerName = getVehicles.customer_name
                                val vehicleNumberPlate = getVehicles.vehicle_number_plate
                                val vehicleLocationDescription =
                                    getVehicles.vehicle_location_description
                                val formattedPhoneNumber = getVehicles.customer_phone_number
                                val currentHours = getVehicles.vehicle_check_in_hours

                                viewModel.sendMessage(
                                    context,
                                    customerName,
                                    vehicleNumberPlate,
                                    vehicleLocationDescription,
                                    formattedPhoneNumber,
                                    currentHours
                                )
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            FlipCard(
                frontContent = {
                    //Calculation Price Operations
                    val hourlyFee = hourlyFeeList.value.firstOrNull()
                    val startDateValue = getVehicles.vehicle_check_in_date
                    hourly1 = hourlyFee?.hourly_v1.toString()
                    hourly2 = hourlyFee?.hourly_v2.toString()
                    hourly3 = hourlyFee?.hourly_v3.toString()
                    hourly4 = hourlyFee?.hourly_v4.toString()
                    hourly5 = hourlyFee?.hourly_v5.toString()
                    daily = hourlyFee?.daily.toString()
                    val result = viewModel.calculateTimeDifference(
                        startDateValue,
                        hourly1,
                        hourly2,
                        hourly3,
                        hourly4,
                        hourly5,
                        daily
                    )
                    elapsedTime = result.first
                    valetFee = result.second.toString()

                    Row(
                        modifier = Modifier
                            .padding(all = 2.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Spacer(modifier = Modifier.size(10.dp))
                            CustomRow(
                                iconRes = R.drawable.baseline_price_change_24,
                                text = valetFee
                            )
                            CustomRow(
                                iconRes = R.drawable.baseline_access_time_filled_24,
                                text = elapsedTime
                            )
                        }
                        FloatingActionButton(
                            modifier = Modifier.padding(top = 25.dp, end = 10.dp),
                            onClick = {
                                viewModel.msgBillButton(
                                    context,
                                    getVehicles.customer_name,
                                    getVehicles.customer_phone_number
                                )
                            },
                            containerColor = colorResource(id = R.color.color_1),
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_done_outline_24),
                                    contentDescription = "", tint = Color.White
                                )
                            }
                        )
                    }
                },
                backContent = {
                    Row(
                        modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column {
                            Spacer(modifier = Modifier.size(32.dp))
                            Text(
                                text = stringResource(id = R.string.time_fee_infos),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.W400,
                                fontStyle = FontStyle.Normal,
                                color = colorResource(id = R.color.black)
                            )
                            Spacer(modifier = Modifier.size(32.dp))
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}

