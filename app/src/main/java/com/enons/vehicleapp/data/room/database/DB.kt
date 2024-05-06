package com.enons.vehicleapp.data.room.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.enons.vehicleapp.data.model.HourlyFee
import com.enons.vehicleapp.data.model.Vehicles
import com.enons.vehicleapp.data.room.dao.VehiclesDao


@Database(entities = [Vehicles::class, HourlyFee::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehiclesDao(): VehiclesDao
}