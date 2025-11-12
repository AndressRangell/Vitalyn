package com.andres.rangel.vitalyn.authentication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andres.rangel.vitalyn.authentication.data.repository.LoginRepositoryImpl
import com.andres.rangel.vitalyn.authentication.domain.usecase.LoginUseCase
import com.andres.rangel.vitalyn.authentication.ui.state.LoginFormState
import com.andres.rangel.vitalyn.authentication.ui.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(

) : ViewModel() {

    private val loginUseCase: LoginUseCase = LoginUseCase(LoginRepositoryImpl())

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private val _loginFormState = MutableStateFlow(LoginFormState())
    val loginFormState: StateFlow<LoginFormState> = _loginFormState.asStateFlow()

    fun togglePasswordVisibility() {
        _loginFormState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onEmailChange(email: String) {
        _loginFormState.update {
            it.copy(
                email = email,
            )
        }
    }

    fun onPasswordChange(password: String) {
        _loginFormState.update {
            it.copy(
                password = password,
            )
        }
    }

    fun login() {
        viewModelScope.launch {
            val state = _loginFormState.value
            _loginUiState.value = LoginUiState.Loading

            loginUseCase(
                email = state.email.trim(),
                password = state.password
            ).fold(
                onSuccess = { user ->
                    _loginUiState.value = LoginUiState.Success
                },
                onFailure = { exception ->
                    _loginFormState.update {
                        it.copy(
                            errorMessage = (exception.cause?.message ?: exception.message) ?: "Unknown error"
                        )
                    }
                    _loginUiState.value = LoginUiState.Idle
                }
            )
        }
    }
}