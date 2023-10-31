package com.yildirim.vehicleapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yildirim.vehicleapp.repo.VehiclesDaoRepository

class VehicleRegisterViewModel(application: Application) : AndroidViewModel(application) {
    var vrepo = VehiclesDaoRepository(application)

    fun register(customer_name:String,vehicle_name:String, vehicle_number_plate:String,vehicle_location_description:String){
        vrepo.registerVehicle(customer_name,vehicle_name,vehicle_number_plate,vehicle_location_description)

    }
}