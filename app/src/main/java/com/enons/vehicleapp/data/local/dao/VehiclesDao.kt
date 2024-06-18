package com.enons.vehicleapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.enons.vehicleapp.data.local.model.HourlyFee
import com.enons.vehicleapp.data.local.model.Vehicles

@Dao
interface VehiclesDao {
    @Query("SELECT * FROM vehicles")
    suspend fun allVehicles(): List<Vehicles>

    @Insert
    suspend fun addVehicle(vehicles: Vehicles)

    @Update
    suspend fun updateVehicle(vehicles: Vehicles)

    @Delete
    suspend fun deleteVehicle(vehicles: Vehicles)

    @Query("SELECT * FROM vehicles WHERE vehicle_number_plate like '%' || :searchPlate || '%'")
    suspend fun searchPlate(searchPlate: String): List<Vehicles>

    @Query("SELECT * FROM hourly_fee")
    suspend fun allHourlyFee(): List<HourlyFee>

    @Update
    suspend fun updateHourlyFee(hourlyFee: HourlyFee)

}