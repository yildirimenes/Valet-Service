package com.yildirim.vehicleapp.ui.screens.register_vehicle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yildirim.vehicleapp.data.repository.VehiclesDaoRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class VehicleRegisterViewModel(application: Application) : AndroidViewModel(application) {
    var vrepo = VehiclesDaoRepository(application)

    fun currentDate(): String {
        val currentDateTime = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(currentDateTime)

    }

    fun currentTime(): String {
        val currentDateTime = Calendar.getInstance().time
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return timeFormat.format(currentDateTime)
    }

    fun register(customerName:String,customerPhoneNumber:String,vehicleName:String, vehicleNumberPlate:String,vehicleLocationDescription:String,vehicleCheckInDate:String,vehicleCheckInHours:String){
        vrepo.registerVehicle(customerName,customerPhoneNumber,vehicleName,vehicleNumberPlate,vehicleLocationDescription,vehicleCheckInDate,vehicleCheckInHours)

    }
}