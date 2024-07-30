package com.enons.vehicleapp.presentation.screens.homePage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.data.repository.VehiclesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val repository: VehiclesRepository
) : ViewModel() {

    var vehicleList = MutableLiveData<List<Vehicles>>()

    init {
        load()
        vehicleList = repository.getVehicles()
    }

    fun load() {
        repository.getAllVehicles()
    }

    fun search(searchPlate: String) {
        repository.searchVehicles(searchPlate)
    }

    fun delete(vehicleId: Int) {
        repository.delVehicle(vehicleId)

    }
}

