package com.enons.vehicleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.presentation.screens.AuthPage.ForgotPasswordPage.ForgotPasswordPage
import com.enons.vehicleapp.presentation.screens.AuthPage.RegisterPage.RegisterPage
import com.enons.vehicleapp.presentation.screens.AuthPage.LoginPage.LoginPage
import com.enons.vehicleapp.presentation.screens.homePage.HomePage
import com.enons.vehicleapp.presentation.screens.hourlyFeePage.HourlyFeePage
import com.enons.vehicleapp.presentation.screens.profitPage.ProfitPage
import com.enons.vehicleapp.presentation.screens.vehicleRegisterPage.VehicleRegisterPage
import com.enons.vehicleapp.presentation.screens.vehicleUpdatePage.VehicleUpdatePage
import com.enons.vehicleapp.presentation.screens.vehiclePage.VehiclePage
import com.google.firebase.auth.FirebaseAuth

sealed class Screen(val route: String) {
    data object HomePage : Screen("home_page")
    data object VehiclePage : Screen("vehicle_page")
    data object VehicleRegisterPage : Screen("vehicle_register_page")
    data object HourlyFeePage : Screen("hourly_fee_page")
    data object VehicleUpdatePage : Screen("vehicle_update_page")
    data object LoginPage : Screen("login_page")
    data object RegisterPage : Screen("register_page")
    data object ForgotPasswordPage : Screen("forgot_password_page")
    data object ProfitPage : Screen("profit_page")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
        Screen.HomePage.route
    } else {
        Screen.LoginPage.route
    }
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.LoginPage.route) {
            LoginPage(navController = navController)
        }
        composable(Screen.ForgotPasswordPage.route) {
            ForgotPasswordPage(navController = navController)
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
        composable(Screen.ProfitPage.route) {
            ProfitPage(navController = navController)
        }
    }
}
