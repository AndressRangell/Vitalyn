package com.andres.rangel.vitalyn.authentication.ui.state

sealed class LoginEvent {
    object LoggedInSuccessfully : LoginEvent()
}