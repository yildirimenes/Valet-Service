package com.yildirim.vehicleapp.ui.screens.register_vehicle
import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.yildirim.vehicleapp.ui.components.CustomButton
import com.yildirim.vehicleapp.ui.components.PhoneField
import com.yildirim.vehicleapp.ui.components.RegisterOutlinedTextField
import com.yildirim.vehicleapp.ui.screens.register_vehicle.viewmodel.VehicleRegisterViewModel
import com.yildirim.vehicleapp.ui.screens.register_vehicle.viewmodel.VehicleRegisterViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun VehicleRegisterPage(navController: NavController){
    val tfCustomerName = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val tfCustomerPhoneNumber = remember { mutableStateOf("") }
    val tfVehicleName = remember { mutableStateOf("") }
    val tfNumberPlate = remember { mutableStateOf("") }
    val tfVehicleLocationDescription = remember { mutableStateOf("") }
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current
    val viewModel : VehicleRegisterViewModel = viewModel(
        factory = VehicleRegisterViewModelFactory(context.applicationContext as Application)
    )
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.car_register))
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

        ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Box(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
            )
            RegisterOutlinedTextField(
                value = tfCustomerName.value,
                onValueChange = {tfCustomerName.value = it},
                label = { Text(text = stringResource(id = R.string.customer_name))}
            )
            Spacer(modifier = Modifier.size(30.dp))
            PhoneField(tfCustomerPhoneNumber.value,
                mask = "(000) 000 00 00",
                maskNumber = '0',
                label = { Text(text = stringResource(id = R.string.customer_phone_number))},
                onPhoneChanged = { tfCustomerPhoneNumber.value = it }
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfVehicleName.value,
                onValueChange = {tfVehicleName.value = it},
                label = { Text(text = stringResource(id = R.string.vehicle_name))}
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfNumberPlate.value,
                onValueChange = {tfNumberPlate.value = it},
                label = { Text(text = stringResource(id = R.string.vehicle_number_plate))}
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfVehicleLocationDescription.value,
                onValueChange = {tfVehicleLocationDescription.value = it},
                label = { Text(text = stringResource(id = R.string.vehicle_location_description))}
            )

            Spacer(modifier = Modifier.size(30.dp))
            CustomButton(
                onClick = {
                    val customerName = tfCustomerName.value
                    val customerPhoneNumber = tfCustomerPhoneNumber.value
                    val vehicleName = tfVehicleName.value
                    val vehicleNumberPlate = tfNumberPlate.value
                    val vehicleLocationDescription = tfVehicleLocationDescription.value
                    val vehicleCheckInDate = viewModel.currentDate()
                    val vehicleCheckInHours = viewModel.currentTime()

                    if (customerName.isNotEmpty() && vehicleName.isNotEmpty() && vehicleNumberPlate.isNotEmpty() && vehicleLocationDescription.isNotEmpty()) {
                        viewModel.register(customerName,customerPhoneNumber ,vehicleName, vehicleNumberPlate, vehicleLocationDescription,vehicleCheckInDate,vehicleCheckInHours)
                        localFocusManager.clearFocus()
                        navController.navigate("category_page")
                    } else {
                        Toast.makeText(context,"Invalid Information", Toast.LENGTH_SHORT).show()
                    }
                },
                text = stringResource(id = R.string.register)
            )
        }
    }
}