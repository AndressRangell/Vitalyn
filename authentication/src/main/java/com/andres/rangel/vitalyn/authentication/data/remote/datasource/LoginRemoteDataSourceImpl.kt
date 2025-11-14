package com.andres.rangel.vitalyn.authentication.data.remote.datasource

import com.andres.rangel.vitalyn.authentication.data.remote.LoginRetrofitProvider
import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginRequestDto
import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginResponseDto

class LoginRemoteDataSourceImpl : ILoginRemoteDataSource {
    override suspend fun login(loginRequestDto: LoginRequestDto): LoginResponseDto {
        try {
            val api = LoginRetrofitProvider.api
            return api.login(loginRequestDto)
        } catch (e: Exception) {
            throw e
        }
    }
}
