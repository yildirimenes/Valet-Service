package com.yildirim.vehicleapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yildirim.vehicleapp.viewmodel.VehicleViewModel

class VehicleViewModelFactory (var application: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleViewModel(application) as  T
    }
}