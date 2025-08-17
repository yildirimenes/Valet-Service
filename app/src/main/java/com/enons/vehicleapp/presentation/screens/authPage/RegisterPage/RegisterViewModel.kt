package com.enons.vehicleapp.presentation.screens.authPage.RegisterPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.repository.FirebaseAuthRepository
import com.enons.vehicleapp.presentation.screens.authPage.LoginPage.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {
    val authState: LiveData<AuthState> = authRepository.authState

    fun signup(email: String, password: String, companyName: String, valetName: String) {
        authRepository.registerUser(email, password, companyName, valetName)
    }
}