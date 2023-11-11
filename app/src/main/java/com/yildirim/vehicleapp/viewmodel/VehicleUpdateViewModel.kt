package com.yildirim.vehicleapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yildirim.vehicleapp.repo.VehiclesDaoRepository

class VehicleUpdateViewModel(application: Application) : AndroidViewModel(application){
    var vrepo = VehiclesDaoRepository(application)

    fun update(vehicleId:Int,customerPhoneNumber:String,customerName:String,vehicleName:String,vehicleNumberPlate:String,vehicleLocationDescription:String,vehicleCheckInDate:String,vehicleCheckInHours:String){
        vrepo.updateVehicle(vehicleId,customerPhoneNumber,customerName,vehicleName, vehicleNumberPlate, vehicleLocationDescription,vehicleCheckInDate,vehicleCheckInHours)
    }
}