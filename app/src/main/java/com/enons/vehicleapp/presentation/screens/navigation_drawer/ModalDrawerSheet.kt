package com.enons.vehicleapp.presentation.screens.navigation_drawer
import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.enons.vehicleapp.R
import com.enons.vehicleapp.presentation.components.CustomSettingsCard
import com.enons.vehicleapp.presentation.screens.navigation_drawer.viewmodel.NavigationDrawerViewModel
import com.enons.vehicleapp.presentation.screens.navigation_drawer.viewmodel.NavigationDrawerViewModelFactory
import com.enons.vehicleapp.util.AppConstant.APP_NAME
import com.enons.vehicleapp.util.AppConstant.CONTACT_MAIL
import com.enons.vehicleapp.util.AppConstant.MAIL_SUBJECT

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheet() {
    val context = LocalContext.current
    val viewModel: NavigationDrawerViewModel = viewModel(
        factory = NavigationDrawerViewModelFactory(context.applicationContext as Application)
    )

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .height(150.dp)
                    .background(colorResource(id = R.color.color_1))
            ) {
                Text(
                    APP_NAME,
                    modifier = Modifier
                        .align(Alignment.Center),
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.W500,
                    fontSize = 40.sp,
                    color = colorResource(id = R.color.color_3),
                    )
            }
            CustomSettingsCard(
                iconRes = R.drawable.baseline_email_24,
                text = stringResource(id = R.string.valet_premium),
                onClick = {
                    viewModel.sendMail(
                        context,
                        to = CONTACT_MAIL,
                        subject = MAIL_SUBJECT
                    )
                })
            CustomSettingsCard(
                iconRes = R.drawable.baseline_thumb_up_alt_24,
                text = stringResource(id = R.string.rate_us),
                onClick = {
                    //viewModel.openPlayStore(context,APP_URL)
                })
        }
    }
}


