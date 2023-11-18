package com.yildirim.vehicleapp.data.room.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yildirim.vehicleapp.data.model.Vehicles
import com.yildirim.vehicleapp.data.room.dao.VehiclesDao


@Database(entities = [Vehicles::class], version = 1)
abstract class DB : RoomDatabase() {
    abstract fun vehicleDao(): VehiclesDao

    companion object{
        private var INSTANCE: DB? = null

        fun databaseAccess(context: Context): DB?{
            if (INSTANCE == null){
                synchronized(DB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DB::class.java,
                        "vehicles.sqlite")
                        .createFromAsset("vehicles.sqlite")
                        //.fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}
