package com.enons.vehicleapp.domain.useCase

import com.enons.vehicleapp.data.repository.FirebaseAuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) {
    fun execute() {
        authRepository.signout()
    }
}
