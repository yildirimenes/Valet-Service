package com.yildirim.vehicleapp.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yildirim.vehicleapp.viewmodel.VehicleRegisterViewModel

class VehicleRegisterViewModelFactory (var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VehicleRegisterViewModel(application) as T
    }
}
