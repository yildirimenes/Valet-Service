package com.enons.vehicleapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.enons.vehicleapp.data.local.dao.VehiclesDao
import com.enons.vehicleapp.data.local.model.HourlyFee
import com.enons.vehicleapp.data.local.model.Vehicles

@Database(entities = [Vehicles::class, HourlyFee::class], version = 1, exportSchema = false)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehiclesDao(): VehiclesDao
}