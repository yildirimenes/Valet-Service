package com.enons.vehicleapp.di

import com.enons.vehicleapp.data.local.dao.DeliveredDao
import com.enons.vehicleapp.data.local.dao.VehiclesDao
import com.enons.vehicleapp.data.repository.VehiclesRepository
import com.enons.vehicleapp.data.repository.VehiclesRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideVehiclesRepository(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth,
        vehiclesDao: VehiclesDao,
        deliveredDao: DeliveredDao
    ): VehiclesRepository {
        return VehiclesRepositoryImpl(firestore, auth, vehiclesDao, deliveredDao)
    }
}