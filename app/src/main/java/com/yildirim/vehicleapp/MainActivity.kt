package com.yildirim.vehicleapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.yildirim.vehicleapp.entity.Vehicles
import com.yildirim.vehicleapp.ui.theme.VehicleAppTheme
import com.yildirim.vehicleapp.view.CategoryPage
import com.yildirim.vehicleapp.view.VehiclePage
import com.yildirim.vehicleapp.view.VehicleRegisterPage
import com.yildirim.vehicleapp.view.VehicleUpdatePage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VehicleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageController()
                }
            }
        }
    }
}

@Composable
fun PageController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category_page"){
        composable("category_page"){
            CategoryPage(navController = navController)
        }
        composable("vehicle_page/{vehicle}", arguments = listOf(
            navArgument("vehicle"){type = NavType.StringType}
        )){
            val json = it.arguments?.getString("vehicle")
            val objects = Gson().fromJson(json,Vehicles::class.java)
            VehiclePage(navController = navController,objects)
        }
        composable("vehicle_register_page"){
            VehicleRegisterPage(navController = navController)

        }
        composable("vehicle_update_page/{vehicle}", arguments = listOf(
            navArgument("vehicle"){type = NavType.StringType}
        )){
            val json = it.arguments?.getString("vehicle")
            val objects = Gson().fromJson(json,Vehicles::class.java)
            VehicleUpdatePage(navController = navController,objects)
        }
    }

}
