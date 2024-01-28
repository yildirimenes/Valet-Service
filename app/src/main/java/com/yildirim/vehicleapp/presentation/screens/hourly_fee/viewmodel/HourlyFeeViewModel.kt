package com.yildirim.vehicleapp.presentation.screens.hourly_fee.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yildirim.vehicleapp.data.model.HourlyFee
import com.yildirim.vehicleapp.data.repository.VehiclesDaoRepository

class HourlyFeeViewModel(application: Application) : AndroidViewModel(application) {
    private var vrepo = VehiclesDaoRepository(application)
    var hourlyFeeList = MutableLiveData<List<HourlyFee>>()

    init {
        load()
        hourlyFeeList = vrepo.getHourlyFee()
    }

    fun load() {
        vrepo.getAllHourlyFee()
    }

    fun update(feeId: Int, hourlyV1: Int, hourlyV2: Int, hourlyV3: Int, hourlyV4: Int, hourlyV5: Int, daily: Int) {
        vrepo.updateHourlyFee(feeId, hourlyV1, hourlyV2, hourlyV3, hourlyV4, hourlyV5, daily)
    }

}