package com.yildirim.vehicleapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yildirim.vehicleapp.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheet() {
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.car_settings)) })
        },
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            CustomSettingsCard(iconRes = R.drawable.baseline_workspace_premium_24, text = stringResource(id = R.string.valet_premium))
            CustomSettingsCard(iconRes = R.drawable.baseline_feed_24, text = stringResource(id = R.string.price_schedule))
            CustomSettingsCard(iconRes = R.drawable.baseline_language_24, text = stringResource(id = R.string.language_option))
            CustomSettingsCard(iconRes = R.drawable.baseline_thumb_up_alt_24, text = stringResource(id = R.string.rate_us))
        }
    }
}