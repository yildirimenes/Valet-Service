package com.enons.vehicleapp.data.repository

import androidx.lifecycle.LiveData
import com.enons.vehicleapp.presentation.screens.LoginPage.viewmodel.AuthState

interface FirebaseAuthRepository {
    val authState: LiveData<AuthState>
    fun loginUser(email: String, password: String)
    fun registerUser(email: String, password: String)
    fun signout()
}