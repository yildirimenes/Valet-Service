package com.yildirim.vehicleapp.ui.screens.register_vehicle.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VehicleRegisterViewModelFactory(var application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleRegisterViewModel(application) as T
    }
}
