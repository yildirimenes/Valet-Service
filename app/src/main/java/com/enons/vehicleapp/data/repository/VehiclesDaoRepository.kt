package com.enons.vehicleapp.data.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.enons.vehicleapp.data.model.HourlyFee
import com.enons.vehicleapp.data.model.Vehicles
import com.enons.vehicleapp.data.room.database.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            vehiclesList.value = vt.vehicleDao().allVehicles()
        }
    }
    fun getAllHourlyFee(){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            hourlyFeeList.value = vt.vehicleDao().allHourlyFee()
        }
    }

    fun searchVehicles(searchPlate:String){
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            vehiclesList.value = vt.vehicleDao().searchPlate(searchPlate)
        }
    }

    fun registerVehicle(customer_name:String,customer_phone_number:String,vehicle_name:String,vehicle_number_plate:String,vehicle_location_description:String,vehicle_check_in_date:String,vehicle_check_in_hours:String){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val newVehicle = Vehicles(0,customer_name,customer_phone_number,vehicle_name,vehicle_number_plate,vehicle_location_description,vehicle_check_in_date,vehicle_check_in_hours)
            vt.vehicleDao().addVehicle(newVehicle)
        }
    }

    fun updateVehicle(vehicle_id:Int,customer_name:String,customer_phone_number:String,vehicle_name:String,vehicle_number_plate:String,vehicle_location_description:String,vehicle_check_in_date:String,vehicle_check_in_hours:String){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val updateVehicle = Vehicles(vehicle_id,customer_name,customer_phone_number,vehicle_name,vehicle_number_plate,vehicle_location_description,vehicle_check_in_date,vehicle_check_in_hours)
            vt.vehicleDao().updateVehicle(updateVehicle)
        }
    }

    fun delVehicle(person_id: Int){
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            val deleteVehicle = Vehicles(person_id,"","","","","","","")
            vt.vehicleDao().deleteVehicle(deleteVehicle)
            getAllVehicles()
        }
    }
    fun getHourlyFee():MutableLiveData<List<HourlyFee>>{
        return hourlyFeeList
    }
    fun updateHourlyFee(fee_id:Int,hourly_v1:Int,hourly_v2:Int,hourly_v3:Int,hourly_v4:Int,hourly_v5:Int,daily:Int){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val updateHourlyFee = HourlyFee(fee_id,hourly_v1,hourly_v2,hourly_v3,hourly_v4,hourly_v5,daily)
            vt.vehicleDao().updateHourlyFee(updateHourlyFee)
        }
    }

}