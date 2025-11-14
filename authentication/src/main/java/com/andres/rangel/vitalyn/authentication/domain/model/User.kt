package com.andres.rangel.vitalyn.authentication.domain.model

import com.andres.rangel.vitalyn.authentication.domain.util.enum.UserState

data class User(
    val email: String,
    val name: String,
    val state: UserState
)