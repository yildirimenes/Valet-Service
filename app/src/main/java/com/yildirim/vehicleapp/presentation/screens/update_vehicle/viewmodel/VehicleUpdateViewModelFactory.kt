package com.yildirim.vehicleapp.presentation.screens.update_vehicle.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VehicleUpdateViewModelFactory(var application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleUpdateViewModel(application) as T
    }
}