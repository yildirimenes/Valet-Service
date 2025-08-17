package com.enons.vehicleapp.data.repository

import androidx.lifecycle.LiveData
import com.enons.vehicleapp.data.remote.model.User
import com.enons.vehicleapp.presentation.screens.authPage.LoginPage.AuthState

interface FirebaseAuthRepository {
    val authState: LiveData<AuthState>
    fun loginUser(email: String, password: String)
    fun registerUser(
        email: String,
        password: String,
        companyName: String,
        valetName: String
    )
    fun sendPasswordReset(email: String, callback: (Boolean, String?) -> Unit)
    fun getUserData(callback: (User?) -> Unit)
    fun signout()
}