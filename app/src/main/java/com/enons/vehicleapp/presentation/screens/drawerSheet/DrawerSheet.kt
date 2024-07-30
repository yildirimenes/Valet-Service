package com.enons.vehicleapp.presentation.screens.drawerSheet

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.enons.vehicleapp.R
import com.enons.vehicleapp.presentation.components.CustomSettingsCard
import com.enons.vehicleapp.presentation.screens.drawerSheet.viewmodel.DrawerViewModel
import com.enons.vehicleapp.utils.AppConstant.APP_NAME
import com.enons.vehicleapp.utils.AppConstant.GOOGLE_PLAY_LINK
import com.enons.vehicleapp.utils.AppConstant.CONTACT_MAIL
import com.enons.vehicleapp.utils.AppConstant.MAIL_SUBJECT

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DrawerSheet() {
    val context = LocalContext.current
    val viewModel: DrawerViewModel = hiltViewModel()

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
                    .background(colorResource(id = R.color.dark_green))
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
                },
            )
            CustomSettingsCard(
                iconRes = R.drawable.baseline_thumb_up_alt_24,
                text = stringResource(id = R.string.rate_us),
                onClick = {
                    viewModel.openPlayStore(context, GOOGLE_PLAY_LINK)
                }
            )
        }
    }
}


