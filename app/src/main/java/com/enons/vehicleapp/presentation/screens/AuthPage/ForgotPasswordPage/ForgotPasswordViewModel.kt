package com.enons.vehicleapp.presentation.screens.AuthPage.ForgotPasswordPage

import androidx.lifecycle.ViewModel
import com.enons.vehicleapp.data.repository.FirebaseAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) : ViewModel() {

    fun sendResetEmail(email: String, callback: (Boolean, String?) -> Unit) {
        authRepository.sendPasswordReset(email, callback)
    }
}
