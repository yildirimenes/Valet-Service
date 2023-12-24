package com.yildirim.vehicleapp.ui.screens.hourly_fee
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
import com.yildirim.vehicleapp.ui.components.RegisterOutlinedTextField
import com.yildirim.vehicleapp.ui.screens.hourly_fee.viewmodel.HourlyFeeViewModel
import com.yildirim.vehicleapp.ui.screens.hourly_fee.viewmodel.HourlyFeeViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable

fun HourlyFeePage(navController: NavController){
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current
    val tfHourlyV1 = remember { mutableStateOf("") }
    val tfHourlyV2 = remember { mutableStateOf("") }
    val tfHourlyV3 = remember { mutableStateOf("") }
    val tfHourlyV4 = remember { mutableStateOf("") }
    val tfHourlyV5 = remember { mutableStateOf("") }
    val tfDaily = remember { mutableStateOf("") }
    val viewModel : HourlyFeeViewModel = viewModel(
        factory = HourlyFeeViewModelFactory(context.applicationContext as Application)
    )

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.car_price_list))
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
                value = tfHourlyV1.value,
                onValueChange = {tfHourlyV1.value = it},
                label = { Text(text = stringResource(id = R.string.hourly_V1)) }
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfHourlyV2.value,
                onValueChange = {tfHourlyV2.value = it},
                label = { Text(text = stringResource(id = R.string.hourly_V2)) }
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfHourlyV3.value,
                onValueChange = {tfHourlyV3.value = it},
                label = { Text(text = stringResource(id = R.string.hourly_V3)) }
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfHourlyV4.value,
                onValueChange = {tfHourlyV4.value = it},
                label = { Text(text = stringResource(id = R.string.hourly_V4)) }
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfHourlyV5.value,
                onValueChange = {tfHourlyV5.value = it},
                label = { Text(text = stringResource(id = R.string.hourly_V5)) }
            )
            Spacer(modifier = Modifier.size(30.dp))
            RegisterOutlinedTextField(
                value = tfDaily.value,
                onValueChange = {tfDaily.value = it},
                label = { Text(text = stringResource(id = R.string.daily)) }
            )

            Spacer(modifier = Modifier.size(30.dp))
            CustomButton(
                onClick = {

                    val hourlyV1 = tfHourlyV1.value
                    val hourlyV2 = tfHourlyV2.value
                    val hourlyV3 = tfHourlyV3.value
                    val hourlyV4 = tfHourlyV4.value
                    val hourlyV5 = tfHourlyV5.value
                    val daily = tfDaily.value

                    if (hourlyV1.isNotEmpty() && hourlyV2.isNotEmpty() && hourlyV3.isNotEmpty() && hourlyV4.isNotEmpty() && hourlyV5.isNotEmpty() && daily.isNotEmpty()) {
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
