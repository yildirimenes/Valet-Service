package com.enons.vehicleapp.presentation.screens.vehicleRegisterPage

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.vehicleapp.R
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.CustomBtn
import com.enons.vehicleapp.presentation.screens.billingSheet.BillingViewModel
import com.enons.vehicleapp.presentation.screens.vehicleRegisterPage.components.RegisterForm

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleRegisterPage(navController: NavController) {
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current
    var tfCustomerName by remember { mutableStateOf("") }
    var tfCustomerPhoneNumber by remember { mutableStateOf("") }
    var tfVehicleLocationDescription by remember { mutableStateOf("") }
    var selectedBrand by remember { mutableStateOf("") }
    var selectedModel by remember { mutableStateOf("") }
    var selectedNumber by remember { mutableStateOf("34") }
    var textFieldValue by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val viewModel: VehicleRegisterViewModel = hiltViewModel()
    val carList by viewModel.carBrands.observeAsState(listOf())
    val vehiclesList by viewModel.vehicleList.observeAsState(listOf())
    var showPremiumDialog by remember { mutableStateOf(false) }
    var showPremiumSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.car_register)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
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
            RegisterForm(
                modifier = Modifier
                    .fillMaxWidth()     ,
                tfCustomerName = tfCustomerName,
                tfCustomerPhoneNumber = tfCustomerPhoneNumber,
                tfVehicleLocationDescription = tfVehicleLocationDescription,
                carList = carList,
                selectedNumber = selectedNumber,
                textFieldValue = textFieldValue,
                onCustomerNameChanged = { tfCustomerName = it },
                onCustomerPhoneNumberChanged = { tfCustomerPhoneNumber = it },
                onVehicleLocationDescriptionChanged = { tfVehicleLocationDescription = it },
                onSelectedBrandChanged = { selectedBrand = it },
                onSelectedModelChanged = { selectedModel = it },
                onSelectedNumberChanged = { selectedNumber = it },
                onTextFieldValueChanged = { textFieldValue = it }
                )

                Spacer(modifier = Modifier.size(20.dp))

            CustomBtn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                containerColor = colorResource(id = R.color.dark_green),
                contentColor = colorResource(id = R.color.color_3),
                onClick = {
                    if (vehiclesList.size >= 10) {
                        showPremiumSheet = true
                    } else {
                        val customerName = tfCustomerName
                        val customerPhoneNumber = tfCustomerPhoneNumber
                        val vehicleName = "$selectedBrand $selectedModel"
                        val vehicleNumberPlate = "$selectedNumber $textFieldValue"
                        val vehicleLocationDescription = tfVehicleLocationDescription
                        val vehicleCheckInDate = viewModel.currentDate()
                        val vehicleCheckInHours = viewModel.currentTime()

                        if (customerName.isNotEmpty() && vehicleName.isNotEmpty() &&
                            vehicleNumberPlate.isNotEmpty() && vehicleLocationDescription.isNotEmpty()
                        ) {
                            viewModel.register(
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
                        } else {
                            Toast.makeText(context, "Eksik bilgi girdiniz", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                text = stringResource(id = R.string.register)
            )

            Spacer(modifier = Modifier.size(30.dp))
        }
    }

    if (showPremiumSheet) {
        ModalBottomSheet(
            onDismissRequest = { showPremiumSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.premium_sheet_title),
                    style = MaterialTheme.typography.titleMedium
                )

                val annotatedText = buildAnnotatedString {
                    append(stringResource(id = R.string.premium_limit_message))
                    append(" ")
                    val tag = "premium"
                    pushStringAnnotation(tag = tag, annotation = tag)
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.dark_green),
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(id = R.string.click_here))
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    onClick = { offset ->
                        annotatedText.getStringAnnotations("premium", offset, offset)
                            .firstOrNull()?.let {
                                showPremiumSheet = false
                                navController.navigate(Screen.PremiumPage.route)
                            }
                    }
                )

                CustomBtn(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    text = stringResource(id = R.string.become_premium_now),
                    containerColor = colorResource(id = R.color.dark_green),
                    contentColor = colorResource(id = R.color.color_3),
                    enabled = false
                )
            }
        }
    }
}
