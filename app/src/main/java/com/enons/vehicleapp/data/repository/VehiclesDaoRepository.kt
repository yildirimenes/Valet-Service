package com.enons.vehicleapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.data.local.model.HourlyFee
import com.enons.vehicleapp.data.local.model.Vehicles
import com.enons.vehicleapp.data.local.dao.VehiclesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VehiclesDaoRepository@Inject constructor(private val dao: VehiclesDao) {
    private var vehiclesList = MutableLiveData<List<Vehicles>>()
    private var hourlyFeeList = MutableLiveData<List<HourlyFee>>()

    init {
        vehiclesList = MutableLiveData()
        hourlyFeeList = MutableLiveData()
    }

    fun getVehicles():MutableLiveData<List<Vehicles>>{
        return vehiclesList
    }

    fun getAllVehicles(){
        CoroutineScope(Dispatchers.Main).launch {
            vehiclesList.value = dao.allVehicles()
        }
    }
    fun getAllHourlyFee(){
        CoroutineScope(Dispatchers.Main).launch {
            hourlyFeeList.value = dao.allHourlyFee()
        }
    }

    fun searchVehicles(searchPlate:String){
        CoroutineScope(Dispatchers.Main).launch {
            vehiclesList.value = dao.searchPlate(searchPlate)
        }
    }

    fun registerVehicle(customerName:String,customerPhoneNumber:String,vehicleName:String,vehicleNumberPlate:String,vehicleLocationDescription:String,vehicleCheckInDate:String,vehicleCheckInHours:String){
        CoroutineScope(Dispatchers.Main).launch {
            val newVehicle = Vehicles(0,customerName,customerPhoneNumber,vehicleName,vehicleNumberPlate,vehicleLocationDescription,vehicleCheckInDate,vehicleCheckInHours)
            dao.addVehicle(newVehicle)
        }
    }

    fun updateVehicle(vehicleId:Int,customerName:String,customerPhoneNumber:String,vehicleName:String,vehicleNumberPlate:String,vehicleLocationDescription:String,vehicleCheckInDate:String,vehicleCheckInHours:String){
        CoroutineScope(Dispatchers.Main).launch {
            val updateVehicle = Vehicles(vehicleId,customerName,customerPhoneNumber,vehicleName,vehicleNumberPlate,vehicleLocationDescription,vehicleCheckInDate,vehicleCheckInHours)
            dao.updateVehicle(updateVehicle)
        }
    }

    fun delVehicle(personId: Int){
        CoroutineScope(Dispatchers.Main).launch {
            val deleteVehicle = Vehicles(personId,"","","","","","","")
            dao.deleteVehicle(deleteVehicle)
            getAllVehicles()
        }
    }
    fun getHourlyFee():MutableLiveData<List<HourlyFee>>{
        return hourlyFeeList
    }
    fun updateHourlyFee(feeId:Int,hourlyV1:Int,hourlyV2:Int,hourlyV3:Int,hourlyV4:Int,hourlyV5:Int,daily:Int){
        CoroutineScope(Dispatchers.Main).launch {
            val updateHourlyFee = HourlyFee(feeId,hourlyV1,hourlyV2,hourlyV3,hourlyV4,hourlyV5,daily)
            dao.updateHourlyFee(updateHourlyFee)
        }
    }

}