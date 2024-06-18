package com.enons.vehicleapp.presentation.screens.CategoryPage.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.data.repository.VehiclesDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: VehiclesDaoRepository) :
    ViewModel() {
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

