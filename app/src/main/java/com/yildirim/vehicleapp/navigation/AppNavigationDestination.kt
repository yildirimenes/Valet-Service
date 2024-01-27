package com.yildirim.vehicleapp.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.yildirim.vehicleapp.data.model.Vehicles
import com.yildirim.vehicleapp.presentation.screens.category.CategoryPage
import com.yildirim.vehicleapp.presentation.screens.hourly_fee.HourlyFeePage
import com.yildirim.vehicleapp.presentation.screens.register_vehicle.VehicleRegisterPage
import com.yildirim.vehicleapp.presentation.screens.update_vehicle.VehicleUpdatePage
import com.yildirim.vehicleapp.presentation.screens.vehicle.VehiclePage

@Composable
fun PageController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category_page") {
        composable("category_page") {
            CategoryPage(navController = navController)
        }
        composable("vehicle_page/{vehicle}", arguments = listOf(
            navArgument("vehicle") { type = NavType.StringType }
        )) {
            val json = it.arguments?.getString("vehicle")
            val objects = Gson().fromJson(json, Vehicles::class.java)
            VehiclePage(navController = navController, objects)
        }
        composable("vehicle_register_page") {
            VehicleRegisterPage(navController = navController)

        }
        composable("hourly_fee_page") {
            HourlyFeePage(navController = navController)

        }
        composable("vehicle_update_page/{vehicle}", arguments = listOf(
            navArgument("vehicle") { type = NavType.StringType }
        )) {
            val json = it.arguments?.getString("vehicle")
            val objects = Gson().fromJson(json, Vehicles::class.java)
            VehicleUpdatePage(navController = navController, objects)
        }
    }
}