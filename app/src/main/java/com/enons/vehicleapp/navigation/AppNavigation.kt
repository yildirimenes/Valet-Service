package com.enons.vehicleapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.presentation.RegisterPage.RegisterScreen
import com.enons.vehicleapp.presentation.RegisterPage.viewmodel.RegisterViewModel
import com.enons.vehicleapp.presentation.screens.LoginPage.LoginScreen
import com.enons.vehicleapp.presentation.screens.LoginPage.viewmodel.LoginViewModel
import com.enons.vehicleapp.presentation.screens.homePage.HomePage
import com.enons.vehicleapp.presentation.screens.hourlyFeePage.HourlyFeePage
import com.enons.vehicleapp.presentation.screens.vehicleRegisterPage.VehicleRegisterPage
import com.enons.vehicleapp.presentation.screens.vehicleUpdatePage.VehicleUpdatePage
import com.enons.vehicleapp.presentation.screens.vehiclePage.VehiclePage

sealed class Screen(val route: String) {
    object HomePage : Screen("category_page")
    object VehiclePage : Screen("vehicle_page")
    object VehicleRegisterPage : Screen("vehicle_register_page")
    object HourlyFeePage : Screen("hourly_fee_page")
    object VehicleUpdatePage : Screen("vehicle_update_page")
    object LoginPage : Screen("login_page")
    object RegisterPage : Screen("register_page")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginPage.route) {
        composable(Screen.LoginPage.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = loginViewModel.uiEffect
            LoginScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = loginViewModel::onAction,
                onNavigateMainScreen = { navController.navigate(Screen.HomePage.route) },
                /*
                onSignOutClick = {
                    loginViewModel.signOut()
                    navController.navigate(Screen.LoginPage.route)
                }*/
            )
        }
        composable(Screen.RegisterPage.route) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = registerViewModel.uiEffect
            RegisterScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = registerViewModel::onAction,
                onNavigateMainScreen = { navController.navigate(Screen.HomePage.route) }
            )
        }
        composable(Screen.HomePage.route) {
            HomePage(navController = navController)
        }
        composable(Screen.VehiclePage.route + "/{vehicle}",
            arguments = listOf(
                navArgument("vehicle") { type = NavType.StringType }
            )) {
            val json = it.arguments?.getString("vehicle")
            val objects = Gson().fromJson(json, Vehicles::class.java)
            VehiclePage(navController = navController, objects)
        }
        composable(Screen.VehicleRegisterPage.route) {
            VehicleRegisterPage(navController = navController)

        }
        composable(Screen.HourlyFeePage.route) {
            HourlyFeePage(navController = navController)

        }
        composable(Screen.VehicleUpdatePage.route + "/{vehicle}",
            arguments = listOf(
                navArgument("vehicle") { type = NavType.StringType }
            )) {
            val json = it.arguments?.getString("vehicle")
            val objects = Gson().fromJson(json, Vehicles::class.java)
            VehicleUpdatePage(navController = navController, objects)
        }
    }
}