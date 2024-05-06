package com.enons.vehicleapp.di

import android.content.Context
import androidx.room.Room
import com.enons.vehicleapp.data.repository.VehiclesDaoRepository
import com.enons.vehicleapp.data.room.dao.VehiclesDao
import com.enons.vehicleapp.data.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "valet.sqlite")
            .createFromAsset("valet.sqlite")
            .build()
    }

    @Provides
    @Singleton
    fun provideVehicleDao(appDatabase: AppDatabase) : VehiclesDao {
        return appDatabase.vehiclesDao()
    }

    @Provides
    @Singleton
    fun provideVehicleRepository(vehiclesDao: VehiclesDao) : VehiclesDaoRepository {
        return VehiclesDaoRepository(vehiclesDao)
    }


}