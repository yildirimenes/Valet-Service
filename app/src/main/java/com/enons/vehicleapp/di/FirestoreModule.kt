package com.enons.vehicleapp.di

import com.enons.vehicleapp.data.repository.CarBrandModelRepository
import com.enons.vehicleapp.data.repository.CarBrandModelRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideCarNameRepository(firestore: FirebaseFirestore): CarBrandModelRepository {
        return CarBrandModelRepositoryImpl(firestore)
    }
}
