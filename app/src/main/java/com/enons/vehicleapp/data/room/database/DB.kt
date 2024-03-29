package com.enons.vehicleapp.data.room.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enons.vehicleapp.data.model.HourlyFee
import com.enons.vehicleapp.data.model.Vehicles
import com.enons.vehicleapp.data.room.dao.VehiclesDao

@Database(entities = [Vehicles::class, HourlyFee::class], version = 1)
abstract class DB : RoomDatabase() {
    abstract fun vehicleDao(): VehiclesDao

    companion object{
        private var INSTANCE: DB? = null

        fun databaseAccess(context: Context): DB?{
            if (INSTANCE == null){
                synchronized(DB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DB::class.java,
                        "valet.sqlite")
                        .createFromAsset("valet.sqlite")
                        //.fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
