package com.enons.vehicleapp.presentation.RegisterPage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {
    val authState: LiveData<AuthState> = authRepository.authState

    fun signup(email: String, password: String) {
        authRepository.signup(email, password)
    }
}
