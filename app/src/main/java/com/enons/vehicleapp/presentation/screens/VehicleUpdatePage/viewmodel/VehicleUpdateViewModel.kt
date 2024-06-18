package com.enons.vehicleapp.presentation.screens.VehicleUpdatePage.viewmodel

import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.repository.VehiclesDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleUpdateViewModel @Inject constructor(private val repository: VehiclesDaoRepository) :
    ViewModel() {
    fun update(
        vehicleId: Int,
        customerPhoneNumber: String,
        customerName: String,
        vehicleName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        vehicleCheckInDate: String,
        vehicleCheckInHours: String
    ) {
        repository.updateVehicle(
            vehicleId,
            customerPhoneNumber,
            customerName,
            vehicleName,
            vehicleNumberPlate,
            vehicleLocationDescription,
            vehicleCheckInDate,
            vehicleCheckInHours
        )
    }
}