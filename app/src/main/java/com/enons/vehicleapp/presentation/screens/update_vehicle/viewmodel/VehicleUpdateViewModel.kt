package com.enons.vehicleapp.presentation.screens.update_vehicle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.enons.vehicleapp.data.repository.VehiclesDaoRepository

class VehicleUpdateViewModel(application: Application) : AndroidViewModel(application){
    private var vrepo = VehiclesDaoRepository(application)

    fun update(vehicleId:Int,customerPhoneNumber:String,customerName:String,vehicleName:String,vehicleNumberPlate:String,vehicleLocationDescription:String,vehicleCheckInDate:String,vehicleCheckInHours:String){
        vrepo.updateVehicle(vehicleId,customerPhoneNumber,customerName,vehicleName, vehicleNumberPlate, vehicleLocationDescription,vehicleCheckInDate,vehicleCheckInHours)
    }
}