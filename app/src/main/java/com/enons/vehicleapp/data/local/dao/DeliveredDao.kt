package com.enons.vehicleapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.enons.vehicleapp.data.local.model.Delivered

@Dao
interface DeliveredDao {
    @Query("SELECT * FROM delivered")
    suspend fun allDelivered(): List<Delivered>

    @Insert
    suspend fun addDelivered(delivered: Delivered)
}