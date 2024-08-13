package com.enons.vehicleapp.presentation.screens.LoginPage.viewmodel

sealed class AuthState{
    data object Authenticated : AuthState()
    data object Unauthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val message : String) : AuthState()
}