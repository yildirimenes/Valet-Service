package com.enons.vehicleapp.presentation.screens.vehicleUpdatePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.remote.model.CarBrand
import com.enons.vehicleapp.data.repository.CarBrandModelRepository
import com.enons.vehicleapp.data.repository.VehiclesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleUpdateViewModel @Inject constructor(
    private val repository: VehiclesRepository,
    private val carBrandModelRepository: CarBrandModelRepository
) : ViewModel() {

    val carBrands: LiveData<List<CarBrand>> = carBrandModelRepository.getCarNames()

    init {
        carBrandModelRepository.fetchCarNames()
    }
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