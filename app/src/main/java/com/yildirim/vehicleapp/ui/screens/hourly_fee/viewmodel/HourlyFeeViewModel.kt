package com.yildirim.vehicleapp.ui.screens.hourly_fee.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yildirim.vehicleapp.data.repository.VehiclesDaoRepository

class HourlyFeeViewModel(application: Application) : AndroidViewModel(application){
    var vrepo = VehiclesDaoRepository(application)

}