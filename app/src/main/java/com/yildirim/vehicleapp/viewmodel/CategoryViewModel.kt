package com.yildirim.vehicleapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yildirim.vehicleapp.entity.Vehicles
import com.yildirim.vehicleapp.repo.VehiclesDaoRepository

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    var vrepo = VehiclesDaoRepository(application)
    var vehicleList = MutableLiveData<List<Vehicles>>()

    init {
        load()
        vehicleList = vrepo.getVehicles()
    }

    fun load(){
        vrepo.getAllVehicles()
    }

    fun search(searchPlate:String){
        vrepo.searchVehicles(searchPlate)
    }

    fun delete(vehicle_id:Int){
        vrepo.delVehicle(vehicle_id)

    }

}