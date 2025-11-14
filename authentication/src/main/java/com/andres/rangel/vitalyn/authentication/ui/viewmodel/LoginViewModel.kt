package com.andres.rangel.vitalyn.authentication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andres.rangel.vitalyn.authentication.data.remote.datasource.LoginRemoteDataSourceImpl
import com.andres.rangel.vitalyn.authentication.data.repository.LoginRepositoryImpl
import com.andres.rangel.vitalyn.authentication.domain.usecase.LoginUseCase
import com.andres.rangel.vitalyn.authentication.ui.state.LoginEvent
import com.andres.rangel.vitalyn.authentication.ui.state.LoginFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginUseCase: LoginUseCase = LoginUseCase()

    // UI events
    private val _loginEvent = Channel<LoginEvent>()
    val loginEvent = _loginEvent.receiveAsFlow()

    private val _loginFormState = MutableStateFlow(LoginFormState())
    val loginFormState: StateFlow<LoginFormState> = _loginFormState.asStateFlow()

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
            _loginFormState.update { it.copy(isLoading = true) }

            loginUseCase(
                email = state.email.trim(),
                password = state.password
            ).fold(
                onSuccess = { user ->
                    clearLoginFormState()
                    _loginEvent.send(LoginEvent.LoggedInSuccessfully)
                },
                onFailure = { exception ->
                    _loginFormState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = (exception.cause?.message ?: exception.message)
                                ?: "Unknown error"
                        )
                    }
                }
            )
        }
    }

    private fun clearLoginFormState() {
        _loginFormState.value = LoginFormState()
    }
}