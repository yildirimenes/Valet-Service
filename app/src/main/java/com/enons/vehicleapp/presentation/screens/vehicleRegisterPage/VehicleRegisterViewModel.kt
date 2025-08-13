package com.enons.vehicleapp.presentation.screens.vehicleRegisterPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.data.remote.model.CarBrand
import com.enons.vehicleapp.data.repository.CarBrandModelRepository
import com.enons.vehicleapp.data.repository.VehiclesRepository
import com.enons.vehicleapp.domain.useCase.GetCurrentDateUseCase
import com.enons.vehicleapp.domain.useCase.GetCurrentTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleRegisterViewModel @Inject constructor(
    private val repository: VehiclesRepository,
    private val carBrandModelRepository: CarBrandModelRepository,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase
) : ViewModel() {

    val carBrands: LiveData<List<CarBrand>> = carBrandModelRepository.getCarNames()
    var vehicleList = MutableLiveData<List<Vehicles>>()

    init {
        carBrandModelRepository.fetchCarNames()
        vehicleList = repository.getVehicles()
        repository.getAllVehicles()
    }

    fun currentDate(): String = getCurrentDateUseCase()
    fun currentTime(): String = getCurrentTimeUseCase()

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