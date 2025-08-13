package com.enons.vehicleapp.presentation.screens.drawerSheet

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.vehicleapp.R
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.CustomSettingsCard
import com.enons.vehicleapp.utils.AppConstant.GOOGLE_PLAY_LINK
import com.enons.vehicleapp.utils.AppConstant.CONTACT_MAIL
import com.enons.vehicleapp.utils.AppConstant.MAIL_SUBJECT

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DrawerSheet(navController: NavController) {
    val context = LocalContext.current
    val viewModel: DrawerViewModel = hiltViewModel()
    val user = viewModel.userData

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(colorResource(id = R.color.dark_green))
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.welcome) + ";",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W500,
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.color_3),
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = user?.valet_name_surname ?: "",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W500,
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.color_3),
                        )
                    }
                }
                CustomSettingsCard(
                    iconRes = R.drawable.baseline_price_change_24,
                    text = stringResource(id = R.string.profit_page),
                    onClick = { navController.navigate(Screen.ProfitPage.route) }
                )
                CustomSettingsCard(
                    iconRes = R.drawable.outline_workspace_premium_24,
                    text = stringResource(id = R.string.premium_page),
                    onClick = { navController.navigate(Screen.PremiumPage.route) },
                )
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
                CustomSettingsCard(
                    iconRes = R.drawable.baseline_logout_24,
                    text = stringResource(id = R.string.sign_out),
                    onClick = {
                        viewModel.signOut(navController)
                    }
                )
            }
            Text(
                text = user?.company_name ?: "",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                color = colorResource(id = R.color.dark_green),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )
        }
    }
}
