package com.andres.rangel.vitalyn.authentication.ui.state

sealed class LoginUiState {
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data object Success : LoginUiState()
}