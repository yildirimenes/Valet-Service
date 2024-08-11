package com.enons.vehicleapp.di

import com.enons.vehicleapp.data.repository.FirebaseAuthRepository
import com.enons.vehicleapp.data.repository.FirebaseAuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth): FirebaseAuthRepository {
        return FirebaseAuthRepositoryImpl(auth)
    }
}