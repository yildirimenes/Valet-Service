package com.enons.vehicleapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "delivered")
data class Delivered(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "delivered_id") @NotNull var delivered_id: Int,
    @ColumnInfo(name = "plate") @NotNull var plate: String,
    @ColumnInfo(name = "price") @NotNull var price: Int,
    @ColumnInfo(name = "date") @NotNull var date: String
)
