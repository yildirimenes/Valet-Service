package com.enons.vehicleapp.presentation.screens.vehicleRegisterPage.viewmodel

import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.repository.VehiclesRepository
import com.enons.vehicleapp.domain.useCase.GetCurrentDateUseCase
import com.enons.vehicleapp.domain.useCase.GetCurrentTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleRegisterViewModel @Inject constructor(
    private val repository: VehiclesRepository,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase
) : ViewModel() {

    fun currentDate(): String {
        return getCurrentDateUseCase()
    }

    fun currentTime(): String {
        return getCurrentTimeUseCase()
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