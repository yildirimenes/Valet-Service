package com.enons.vehicleapp.presentation.RegisterPage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.vehicleapp.common.Resource
import com.enons.vehicleapp.data.repository.AuthRepository
import com.enons.vehicleapp.presentation.RegisterPage.RegisterContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterContract.UiState())
    val uiState: StateFlow<RegisterContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<RegisterContract.UiEffect>() }
    val uiEffect: Flow<RegisterContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: RegisterContract.UiAction) {
        when (uiAction) {
            is RegisterContract.UiAction.SignUpClick -> signUp()
            is RegisterContract.UiAction.ChangeEmail -> updateUiState { copy(email = uiAction.email) }
            is RegisterContract.UiAction.ChangePassword -> updateUiState { copy(password = uiAction.password) }
        }
    }

    private fun signUp() = viewModelScope.launch {
        when (val result = authRepository.signUp(uiState.value.email, uiState.value.password)) {
            is Resource.Success -> {
                emitUiEffect(RegisterContract.UiEffect.ShowToast(result.data))
                emitUiEffect(RegisterContract.UiEffect.GoToMainScreen)
            }

            is Resource.Error -> {
                emitUiEffect(RegisterContract.UiEffect.ShowToast(result.exception.message.orEmpty()))
            }
        }
    }

    private fun updateUiState(block: RegisterContract.UiState.() -> RegisterContract.UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: RegisterContract.UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
