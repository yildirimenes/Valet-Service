package com.enons.vehicleapp.di

import com.enons.vehicleapp.data.local.dao.VehiclesDao
import com.enons.vehicleapp.data.repository.AuthRepository
import com.enons.vehicleapp.data.repository.VehiclesRepository
import com.enons.vehicleapp.data.repository.VehiclesRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
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

    @Provides
    @Singleton
    fun bindAuthRepository(auth: FirebaseAuth): AuthRepository = AuthRepository(auth)
}