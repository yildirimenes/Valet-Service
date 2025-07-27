package com.enons.vehicleapp.presentation.screens.AuthPage.ForgotPasswordPage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.vehicleapp.R
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.AuthBtn
import com.enons.vehicleapp.presentation.components.EmailAuthOutlinedTextField

@Composable
fun ForgotPasswordPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val emailState = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = stringResource(id = R.string.reset_password_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        EmailAuthOutlinedTextField(
            value = emailState.value.trim(),
            onValueChange = { emailState.value = it.trim() },
            label = { Text(text = stringResource(id = R.string.email)) },
            leadingIcon = Icons.Default.Email
        )
        AuthBtn(
            onClick = {
                val email = emailState.value
                viewModel.sendResetEmail(email) { success, message ->
                    if (success) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.reset_password_success),
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(Screen.LoginPage.route)
                    } else {
                        Toast.makeText(
                            context,
                            message ?: context.getString(R.string.invalid_information),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            },
            text = stringResource(id = R.string.send_reset_email)
        )
        HorizontalDivider(
            modifier = Modifier.padding(top = 48.dp),
            thickness = 1.dp,
            color = Color.DarkGray.copy(alpha = 0.3f)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(id = R.string.register_text), color = Color.DarkGray)
            TextButton(onClick = { navController.navigate(Screen.LoginPage.route) }) {
                Text(
                    stringResource(id = R.string.auth_login),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
