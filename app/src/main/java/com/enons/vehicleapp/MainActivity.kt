package com.enons.vehicleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.enons.vehicleapp.navigation.AppNavigation
import com.enons.vehicleapp.presentation.screens.onboardingPage.OnBoardingScreen
import com.enons.vehicleapp.utils.OnBoardingUtils
import com.enons.vehicleapp.presentation.ui.theme.VehicleAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val onBoardingUtils by lazy {
        OnBoardingUtils(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            VehicleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if(onBoardingUtils.isOnBoardingComplete()){
                        AppNavigation()
                    }
                    else{
                        ShowOnBoardingScreen()
                    }
                }
            }
        }
    }
    @Composable
    private fun ShowOnBoardingScreen(){
        val scope = rememberCoroutineScope()
        OnBoardingScreen {
            onBoardingUtils.setOnBoardingComplete()
            scope.launch {
                setContent {
                    AppNavigation()
                }
            }
        }
    }

}





