package com.andres.rangel.vitalyn.authentication.data.remote

import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginRequestDto
import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDto): LoginResponseDto
}