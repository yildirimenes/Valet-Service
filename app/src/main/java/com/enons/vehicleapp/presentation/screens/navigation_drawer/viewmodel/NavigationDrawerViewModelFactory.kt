package com.enons.vehicleapp.presentation.screens.navigation_drawer.viewmodel
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NavigationDrawerViewModelFactory(var application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NavigationDrawerViewModel(application) as T
    }
}
