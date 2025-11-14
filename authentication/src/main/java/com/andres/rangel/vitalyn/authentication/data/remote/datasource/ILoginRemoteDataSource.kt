package com.andres.rangel.vitalyn.authentication.data.remote.datasource

import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginRequestDto
import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginResponseDto

interface ILoginRemoteDataSource {
    suspend fun login(loginRequestDto: LoginRequestDto): LoginResponseDto
}