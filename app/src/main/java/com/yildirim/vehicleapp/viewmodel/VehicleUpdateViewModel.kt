package com.yildirim.vehicleapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yildirim.vehicleapp.repo.VehiclesDaoRepository

class VehicleUpdateViewModel(application: Application) : AndroidViewModel(application){
    var vrepo = VehiclesDaoRepository(application)

    fun update(vehicle_id:Int,customer_phone_number:String,customer_name:String,vehicle_name:String,vehicle_number_plate:String,vehicle_location_description:String){
        vrepo.updateVehicle(vehicle_id,customer_phone_number,customer_name,vehicle_name, vehicle_number_plate, vehicle_location_description)
    }
}