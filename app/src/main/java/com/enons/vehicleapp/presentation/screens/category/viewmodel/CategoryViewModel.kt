package com.enons.vehicleapp.presentation.screens.category.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.data.model.Vehicles
import com.enons.vehicleapp.data.repository.VehiclesDaoRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private var vrepo = VehiclesDaoRepository(application)
    var vehicleList = MutableLiveData<List<Vehicles>>()

    init {
        load()
        vehicleList = vrepo.getVehicles()
    }

    fun load() {
        vrepo.getAllVehicles()
    }

    fun search(searchPlate: String) {
        vrepo.searchVehicles(searchPlate)
    }

    fun delete(vehicleId: Int) {
        vrepo.delVehicle(vehicleId)

    }

}