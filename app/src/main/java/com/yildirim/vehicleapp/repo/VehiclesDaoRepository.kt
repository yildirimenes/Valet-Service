package com.yildirim.vehicleapp.repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.yildirim.vehicleapp.entity.Vehicles
import com.yildirim.vehicleapp.room.DB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class VehiclesDaoRepository(var application: Application) {
    var vehiclesList = MutableLiveData<List<Vehicles>>()
    var vt:DB

    init {
        vt = DB.databaseAccess(application)!!
        vehiclesList = MutableLiveData()
    }

    fun getVehicles():MutableLiveData<List<Vehicles>>{
        return vehiclesList
    }

    fun getAllVehicles(){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            vehiclesList.value = vt.vehicleDao().allVehicles()
        }
    }

    fun searchVehicles(searchPlate:String){
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            vehiclesList.value = vt.vehicleDao().searchPlate(searchPlate)
        }
    }

    fun registerVehicle(customer_name:String,vehicle_name:String,vehicle_number_plate:String,vehicle_location_description:String){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val newVehicle = Vehicles(0,customer_name,vehicle_name,vehicle_number_plate,vehicle_location_description)
            vt.vehicleDao().addVehicle(newVehicle)
        }
    }

    fun updateVehicle(vehicle_id:Int,customer_name:String,vehicle_name:String,vehicle_number_plate:String,vehicle_location_description:String){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val updateVehicle = Vehicles(vehicle_id,customer_name,vehicle_name,vehicle_number_plate,vehicle_location_description)
            vt.vehicleDao().updateVehicle(updateVehicle)
        }
    }
    fun delVehicle(person_id: Int){
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            val deleteVehicle = Vehicles(person_id,"","","","")
            vt.vehicleDao().deleteVehicle(deleteVehicle)
            getAllVehicles()
        }
    }
}