package com.enons.vehicleapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "hourly_fee")
data class HourlyFee(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fee_id") @NotNull var fee_id: Int,
    @ColumnInfo(name = "hourly_v1") @NotNull var hourly_v1: Int,
    @ColumnInfo(name = "hourly_v2") @NotNull var hourly_v2: Int,
    @ColumnInfo(name = "hourly_v3") @NotNull var hourly_v3: Int,
    @ColumnInfo(name = "hourly_v4") @NotNull var hourly_v4: Int,
    @ColumnInfo(name = "hourly_v5") @NotNull var hourly_v5: Int,
    @ColumnInfo(name = "daily") @NotNull var daily: Int
)



