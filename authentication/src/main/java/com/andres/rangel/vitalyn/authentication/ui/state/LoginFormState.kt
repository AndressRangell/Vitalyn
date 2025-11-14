package com.andres.rangel.vitalyn.authentication.ui.state

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = false
)
