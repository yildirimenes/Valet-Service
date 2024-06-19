package com.enons.vehicleapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.data.local.model.HourlyFee
import com.enons.vehicleapp.data.local.model.Vehicles


interface VehiclesRepository {

    fun getVehicles(): MutableLiveData<List<Vehicles>>

    fun getAllVehicles()

    fun getHourlyFee(): MutableLiveData<List<HourlyFee>>

    fun getAllHourlyFee()

    fun searchVehicles(searchPlate: String)

    fun registerVehicle(
        customerName: String,
        customerPhoneNumber: String,
        vehicleName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        vehicleCheckInDate: String,
        vehicleCheckInHours: String
    )

    fun updateVehicle(
        vehicleId: Int,
        customerName: String,
        customerPhoneNumber: String,
        vehicleName: String,
        vehicleNumberPlate: String,
        vehicleLocationDescription: String,
        vehicleCheckInDate: String,
        vehicleCheckInHours: String
    )

    fun updateHourlyFee(
        feeId: Int,
        hourlyV1: Int,
        hourlyV2: Int,
        hourlyV3: Int,
        hourlyV4: Int,
        hourlyV5: Int,
        daily: Int
    )

    fun delVehicle(personId: Int)

}
