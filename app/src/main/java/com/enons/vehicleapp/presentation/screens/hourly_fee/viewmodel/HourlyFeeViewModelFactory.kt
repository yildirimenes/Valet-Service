package com.enons.vehicleapp.presentation.screens.hourly_fee.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HourlyFeeViewModelFactory(var application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HourlyFeeViewModel(application) as T
    }
}