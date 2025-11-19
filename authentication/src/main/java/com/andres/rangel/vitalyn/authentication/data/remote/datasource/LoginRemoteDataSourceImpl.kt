package com.andres.rangel.vitalyn.authentication.data.remote.datasource

import com.andres.rangel.vitalyn.authentication.data.remote.ILoginApiService
import com.andres.rangel.vitalyn.authentication.data.remote.LoginRetrofitProvider
import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginRequestDto
import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginResponseDto

class LoginRemoteDataSourceImpl : ILoginRemoteDataSource {
    override suspend fun login(loginRequestDto: LoginRequestDto): LoginResponseDto? {
        val api = LoginRetrofitProvider.retrofit?.create(ILoginApiService::class.java)
        return api?.login(loginRequestDto)
            ?: throw Exception("No se pudo conectar con el servidor")
    }
}
