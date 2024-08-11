package com.enons.vehicleapp.di

import com.enons.vehicleapp.data.local.dao.VehiclesDao
import com.enons.vehicleapp.data.repository.VehiclesRepository
import com.enons.vehicleapp.data.repository.VehiclesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideVehiclesRepository(vehiclesDao: VehiclesDao): VehiclesRepository {
        return VehiclesRepositoryImpl(vehiclesDao)
    }
}