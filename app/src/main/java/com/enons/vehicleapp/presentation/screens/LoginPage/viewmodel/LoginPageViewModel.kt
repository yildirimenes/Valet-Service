package com.enons.vehicleapp.presentation.screens.LoginPage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {

    val authState: LiveData<AuthState> = authRepository.authState

    fun login(email: String, password: String) {
        authRepository.login(email, password)
    }
}



