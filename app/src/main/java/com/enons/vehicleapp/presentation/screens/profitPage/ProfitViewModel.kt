package com.enons.vehicleapp.presentation.screens.profitPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.local.model.Delivered
import com.enons.vehicleapp.data.repository.VehiclesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfitViewModel @Inject constructor(
    private val repository: VehiclesRepository
) : ViewModel() {

    var deliveredList = MutableLiveData<List<Delivered>>()

    init {
        load()
        deliveredList = repository.getDeliveredVehicles()
    }

    fun load() {
        repository.getAllDeliveredVehicles()
    }

    fun deleteDelivered(id: Int) {
        repository.deleteDeliveredVehicle(id)
    }

    fun deleteAllDelivered() {
        repository.deleteAllDelivered()
    }

}
