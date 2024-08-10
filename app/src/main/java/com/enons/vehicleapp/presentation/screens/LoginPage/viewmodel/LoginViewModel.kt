package com.enons.vehicleapp.presentation.screens.LoginPage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enons.vehicleapp.common.Resource
import com.enons.vehicleapp.data.repository.AuthRepository
import com.enons.vehicleapp.presentation.screens.LoginPage.LoginContract
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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginContract.UiState())
    val uiState: StateFlow<LoginContract.UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<LoginContract.UiEffect>() }
    val uiEffect: Flow<LoginContract.UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        isUserLoggedIn()
    }

    fun onAction(uiAction: LoginContract.UiAction) {
        when (uiAction) {
            is LoginContract.UiAction.SignInClick -> signIn()
            is LoginContract.UiAction.ChangeEmail -> updateUiState { copy(email = uiAction.email) }
            is LoginContract.UiAction.ChangePassword -> updateUiState { copy(password = uiAction.password) }
        }
    }

    private fun isUserLoggedIn() = viewModelScope.launch {
        if (authRepository.isUserLoggedIn()) {
            emitUiEffect(LoginContract.UiEffect.GoToMainScreen)
        }
    }

    private fun signIn() = viewModelScope.launch {
        when (val result = authRepository.signIn(uiState.value.email, uiState.value.password)) {
            is Resource.Success -> {
                emitUiEffect(LoginContract.UiEffect.ShowToast(result.data))
                emitUiEffect(LoginContract.UiEffect.GoToMainScreen)
            }

            is Resource.Error -> {
                emitUiEffect(LoginContract.UiEffect.ShowToast(result.exception.message.orEmpty()))
            }
        }
    }

    private fun updateUiState(block: LoginContract.UiState.() -> LoginContract.UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: LoginContract.UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
