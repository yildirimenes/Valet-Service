package com.enons.vehicleapp.presentation.screens.hourlyFeePage

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
import androidx.compose.runtime.livedata.observeAsState
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
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.CustomBtn
import com.enons.vehicleapp.presentation.components.HourlyFeeOutlinedTextField
import com.enons.vehicleapp.presentation.screens.hourlyFeePage.viewmodel.HourlyFeeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable

fun HourlyFeePage(navController: NavController) {
    val localFocusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    var tfHourlyV1 by remember { mutableStateOf("") }
    var tfHourlyV2 by remember { mutableStateOf("") }
    var tfHourlyV3 by remember { mutableStateOf("") }
    var tfHourlyV4 by remember { mutableStateOf("") }
    var tfHourlyV5 by remember { mutableStateOf("") }
    var tfDaily by remember { mutableStateOf("") }
    val viewModel: HourlyFeeViewModel = hiltViewModel()
    val hourlyFeeList = viewModel.hourlyFeeList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        viewModel.load()
    }

    val hourlyFee = hourlyFeeList.value.firstOrNull()
    tfHourlyV1 = hourlyFee?.hourly_v1.toString()
    tfHourlyV2 = hourlyFee?.hourly_v2.toString()
    tfHourlyV3 = hourlyFee?.hourly_v3.toString()
    tfHourlyV4 = hourlyFee?.hourly_v4.toString()
    tfHourlyV5 = hourlyFee?.hourly_v5.toString()
    tfDaily = hourlyFee?.daily.toString()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.car_price_list))
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
                .verticalScroll(scrollState)
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HourlyFeeOutlinedTextField(
                value = tfHourlyV1,
                onValueChange = { tfHourlyV1 = it },
                label = { Text(text = stringResource(id = R.string.hourly_V1)) }
            )
            Spacer(modifier = Modifier.size(20.dp))
            HourlyFeeOutlinedTextField(
                value = tfHourlyV2,
                onValueChange = { tfHourlyV2 = it },
                label = { Text(text = stringResource(id = R.string.hourly_V2)) }
            )
            Spacer(modifier = Modifier.size(20.dp))
            HourlyFeeOutlinedTextField(
                value = tfHourlyV3,
                onValueChange = { tfHourlyV3 = it },
                label = { Text(text = stringResource(id = R.string.hourly_V3)) }
            )
            Spacer(modifier = Modifier.size(20.dp))
            HourlyFeeOutlinedTextField(
                value = tfHourlyV4,
                onValueChange = { tfHourlyV4 = it },
                label = { Text(text = stringResource(id = R.string.hourly_V4)) }
            )
            Spacer(modifier = Modifier.size(20.dp))
            HourlyFeeOutlinedTextField(
                value = tfHourlyV5,
                onValueChange = { tfHourlyV5 = it },
                label = { Text(text = stringResource(id = R.string.hourly_V5)) }
            )
            Spacer(modifier = Modifier.size(20.dp))
            HourlyFeeOutlinedTextField(
                value = tfDaily,
                onValueChange = { tfDaily = it },
                label = { Text(text = stringResource(id = R.string.daily)) }
            )
            Spacer(modifier = Modifier.size(20.dp))
            CustomBtn(
                modifier = Modifier.fillMaxWidth(0.7f),
                containerColor = colorResource(id = R.color.dark_green),
                contentColor = colorResource(id = R.color.color_3),
                onClick = {
                    val hourlyV1 = tfHourlyV1.toInt()
                    val hourlyV2 = tfHourlyV2.toInt()
                    val hourlyV3 = tfHourlyV3.toInt()
                    val hourlyV4 = tfHourlyV4.toInt()
                    val hourlyV5 = tfHourlyV5.toInt()
                    val daily = tfDaily.toInt()
                    viewModel.update(1, hourlyV1, hourlyV2, hourlyV3, hourlyV4, hourlyV5, daily)
                    localFocusManager.clearFocus()
                    navController.navigate(Screen.HomePage.route)

                },
                text = stringResource(id = R.string.update),
            )
            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}
