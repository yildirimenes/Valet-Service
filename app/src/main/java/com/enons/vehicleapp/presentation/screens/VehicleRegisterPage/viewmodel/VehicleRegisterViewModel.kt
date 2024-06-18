package com.enons.vehicleapp.presentation.screens.VehicleRegisterPage.viewmodel

import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.repository.VehiclesDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class VehicleRegisterViewModel @Inject constructor(private val repository: VehiclesDaoRepository) :
    ViewModel() {
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

    fun register(
        customerName: String,
        customerPhoneNumber: String,
        vehicleName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        vehicleCheckInDate: String,
        vehicleCheckInHours: String
    ) {
        repository.registerVehicle(
            customerName,
            customerPhoneNumber,
            vehicleName,
            vehicleNumberPlate,
            vehicleLocationDescription,
            vehicleCheckInDate,
            vehicleCheckInHours
        )

    }
}