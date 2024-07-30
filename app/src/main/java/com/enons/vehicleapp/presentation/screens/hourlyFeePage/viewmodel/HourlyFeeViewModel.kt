package com.enons.vehicleapp.presentation.screens.hourlyFeePage.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.local.model.HourlyFee
import com.enons.vehicleapp.data.repository.VehiclesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HourlyFeeViewModel @Inject constructor(
    private val repository: VehiclesRepository
) : ViewModel() {

    var hourlyFeeList = MutableLiveData<List<HourlyFee>>()

    init {
        load()
        hourlyFeeList = repository.getHourlyFee()
    }

    fun load() {
        repository.getAllHourlyFee()
    }

    fun update(feeId: Int, hourlyV1: Int, hourlyV2: Int, hourlyV3: Int, hourlyV4: Int, hourlyV5: Int, daily: Int) {
        repository.updateHourlyFee(feeId, hourlyV1, hourlyV2, hourlyV3, hourlyV4, hourlyV5, daily)
    }
}