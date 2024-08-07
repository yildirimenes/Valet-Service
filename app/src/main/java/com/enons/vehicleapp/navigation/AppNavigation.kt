package com.enons.vehicleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.presentation.screens.homePage.HomePage
import com.enons.vehicleapp.presentation.screens.hourlyFeePage.HourlyFeePage
import com.enons.vehicleapp.presentation.screens.vehicleRegisterPage.VehicleRegisterPage
import com.enons.vehicleapp.presentation.screens.vehicleUpdatePage.VehicleUpdatePage
import com.enons.vehicleapp.presentation.screens.vehiclePage.VehiclePage

sealed class Screen(val route: String) {
    object CategoryPage : Screen("category_page")
    object VehiclePage : Screen("vehicle_page")
    object VehicleRegisterPage : Screen("vehicle_register_page")
    object HourlyFeePage : Screen("hourly_fee_page")
    object VehicleUpdatePage : Screen("vehicle_update_page")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CategoryPage.route) {
        composable(Screen.CategoryPage.route) {
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