package com.enons.vehicleapp.data.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.data.model.HourlyFee
import com.enons.vehicleapp.data.model.Vehicles
import com.enons.vehicleapp.data.room.database.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehiclesDaoRepository(var application: Application) {
    var vehiclesList = MutableLiveData<List<Vehicles>>()
    var hourlyFeeList = MutableLiveData<List<HourlyFee>>()
    var vt: DB

    init {
        vt = DB.databaseAccess(application)!!
        vehiclesList = MutableLiveData()
        hourlyFeeList = MutableLiveData()
    }

    fun getVehicles():MutableLiveData<List<Vehicles>>{
        return vehiclesList
    }

    fun getAllVehicles(){
        CoroutineScope(Dispatchers.Main).launch {
            vehiclesList.value = vt.vehicleDao().allVehicles()
        }
    }
    fun getAllHourlyFee(){
        CoroutineScope(Dispatchers.Main).launch {
            hourlyFeeList.value = vt.vehicleDao().allHourlyFee()
        }
    }

    fun searchVehicles(searchPlate:String){
        CoroutineScope(Dispatchers.Main).launch {
            vehiclesList.value = vt.vehicleDao().searchPlate(searchPlate)
        }
    }

    fun registerVehicle(customerName:String,customerPhoneNumber:String,vehicleName:String,vehicleNumberPlate:String,vehicleLocationDescription:String,vehicleCheckInDate:String,vehicleCheckInHours:String){
        CoroutineScope(Dispatchers.Main).launch {
            val newVehicle = Vehicles(0,customerName,customerPhoneNumber,vehicleName,vehicleNumberPlate,vehicleLocationDescription,vehicleCheckInDate,vehicleCheckInHours)
            vt.vehicleDao().addVehicle(newVehicle)
        }
    }

    fun updateVehicle(vehicleId:Int,customerName:String,customerPhoneNumber:String,vehicleName:String,vehicleNumberPlate:String,vehicleLocationDescription:String,vehicleCheckInDate:String,vehicleCheckInHours:String){
        CoroutineScope(Dispatchers.Main).launch {
            val updateVehicle = Vehicles(vehicleId,customerName,customerPhoneNumber,vehicleName,vehicleNumberPlate,vehicleLocationDescription,vehicleCheckInDate,vehicleCheckInHours)
            vt.vehicleDao().updateVehicle(updateVehicle)
        }
    }

    fun delVehicle(personId: Int){
        CoroutineScope(Dispatchers.Main).launch {
            val deleteVehicle = Vehicles(personId,"","","","","","","")
            vt.vehicleDao().deleteVehicle(deleteVehicle)
            getAllVehicles()
        }
    }
    fun getHourlyFee():MutableLiveData<List<HourlyFee>>{
        return hourlyFeeList
    }
    fun updateHourlyFee(feeId:Int,hourlyV1:Int,hourlyV2:Int,hourlyV3:Int,hourlyV4:Int,hourlyV5:Int,daily:Int){
        CoroutineScope(Dispatchers.Main).launch {
            val updateHourlyFee = HourlyFee(feeId,hourlyV1,hourlyV2,hourlyV3,hourlyV4,hourlyV5,daily)
            vt.vehicleDao().updateHourlyFee(updateHourlyFee)
        }
    }

}