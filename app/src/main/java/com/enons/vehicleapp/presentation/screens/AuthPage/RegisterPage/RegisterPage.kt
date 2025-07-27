package com.enons.vehicleapp.presentation.screens.AuthPage.RegisterPage

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enons.vehicleapp.R
import com.enons.vehicleapp.navigation.Screen
import com.enons.vehicleapp.presentation.components.EmailAuthOutlinedTextField
import com.enons.vehicleapp.presentation.components.AuthBtn
import com.enons.vehicleapp.presentation.components.PasswordOutlinedTextField
import com.enons.vehicleapp.presentation.screens.AuthPage.LoginPage.AuthState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var repeatPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordFocused by remember { mutableStateOf(false) }
    var isRepeatFocused by remember { mutableStateOf(false) }

    val authState by registerViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> navController.navigate(Screen.LoginPage.route)
            is AuthState.Error -> Toast.makeText(
                context,
                (authState as AuthState.Error).message, Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )

        EmailAuthOutlinedTextField(
            value = email.trim(),
            onValueChange = { email = it.trim() },
            label = { Text(text = stringResource(id = R.string.email)) },
            leadingIcon = Icons.Default.Email,
        )

        PasswordOutlinedTextField(
            password = password,
            onPasswordChange = { password = it },
            label = { Text(text = stringResource(id = R.string.password)) },
            passwordVisible = passwordVisible,
            onPasswordVisibilityChange = { passwordVisible = !passwordVisible },
            isPasswordFocused = isPasswordFocused,
            onFocusChange = { isPasswordFocused = it.isFocused }
        )

        PasswordOutlinedTextField(
            password = repeatPassword,
            onPasswordChange = { repeatPassword = it },
            label = { Text(text = stringResource(id = R.string.password_repeat)) },
            passwordVisible = repeatPasswordVisible,
            onPasswordVisibilityChange = { repeatPasswordVisible = !repeatPasswordVisible },
            isPasswordFocused = isRepeatFocused,
            onFocusChange = { isRepeatFocused = it.isFocused }
        )

        AuthBtn(
            onClick = {
                if (password != repeatPassword) {
                    Toast.makeText(context, "Şifreler eşleşmiyor", Toast.LENGTH_SHORT).show()
                    return@AuthBtn
                }
                registerViewModel.signup(email, password)
            },
            text = stringResource(id = R.string.auth_register),
            enabled = authState != AuthState.Loading
        )

        HorizontalDivider(
            modifier = Modifier.padding(top = 48.dp),
            thickness = 1.dp,
            color = Color.DarkGray.copy(alpha = 0.3f)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(id = R.string.register_text), color = Color.DarkGray)
            TextButton(onClick = {
                navController.navigate(Screen.LoginPage.route)
            }) {
                Text(
                    stringResource(id = R.string.auth_login),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

