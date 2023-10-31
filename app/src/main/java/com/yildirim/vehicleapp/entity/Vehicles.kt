package com.yildirim.vehicleapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "vehicles")
data class Vehicles(@PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "vehicle_id") @NotNull var vehicle_id:Int,
                    @ColumnInfo(name = "customer_name") @NotNull var customer_name:String,
                    @ColumnInfo(name = "vehicle_name") @NotNull var vehicle_name:String,
                    @ColumnInfo(name = "vehicle_number_plate") @NotNull var vehicle_number_plate:String,
                    @ColumnInfo(name = "vehicle_location_description") @NotNull var vehicle_location_description:String){
}