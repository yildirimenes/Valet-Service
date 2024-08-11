package com.enons.vehicleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.presentation.RegisterPage.RegisterPage
import com.enons.vehicleapp.presentation.screens.LoginPage.LoginPage
import com.enons.vehicleapp.presentation.screens.homePage.HomePage
import com.enons.vehicleapp.presentation.screens.hourlyFeePage.HourlyFeePage
import com.enons.vehicleapp.presentation.screens.vehicleRegisterPage.VehicleRegisterPage
import com.enons.vehicleapp.presentation.screens.vehicleUpdatePage.VehicleUpdatePage
import com.enons.vehicleapp.presentation.screens.vehiclePage.VehiclePage

sealed class Screen(val route: String) {
    data object HomePage : Screen("home_page")
    data object VehiclePage : Screen("vehicle_page")
    data object VehicleRegisterPage : Screen("vehicle_register_page")
    data object HourlyFeePage : Screen("hourly_fee_page")
    data object VehicleUpdatePage : Screen("vehicle_update_page")
    data object LoginPage : Screen("login_page")
    data object RegisterPage : Screen("register_page")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginPage.route) {
        composable(Screen.LoginPage.route) {
            LoginPage(navController = navController)
        }
        composable(Screen.RegisterPage.route) {
            RegisterPage(navController = navController)
        }
        composable(Screen.HomePage.route) {
            HomePage(navController = navController)
        }
        composable(Screen.VehiclePage.route + "/{vehicle}",
            arguments = listOf(
                navArgument("vehicle") { type = NavType.StringType }
            )) {
            val json = it.arguments?.getString("vehicle")
            val vehicle = Gson().fromJson(json, Vehicles::class.java)
            VehiclePage(navController = navController, vehicle)
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
            val vehicle = Gson().fromJson(json, Vehicles::class.java)
            VehicleUpdatePage(navController = navController, vehicle)
        }
    }
}
