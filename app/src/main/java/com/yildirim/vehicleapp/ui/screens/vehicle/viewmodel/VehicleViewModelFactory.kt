package com.yildirim.vehicleapp.ui.screens.vehicle.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VehicleViewModelFactory (var application: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleViewModel(application) as  T
    }
}