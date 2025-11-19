package com.andres.rangel.vitalyn.authentication.data.mapper

import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginResponseDto
import com.andres.rangel.vitalyn.authentication.domain.model.User
import com.andres.rangel.vitalyn.authentication.domain.util.enum.UserState

fun LoginResponseDto.toDomain() = User(
    email = email,
    name = name,
    state = UserState.fromId(id = state) ?: UserState.UNDEFINED
)

