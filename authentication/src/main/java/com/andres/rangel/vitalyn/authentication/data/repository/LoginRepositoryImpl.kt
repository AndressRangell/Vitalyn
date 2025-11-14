package com.andres.rangel.vitalyn.authentication.data.repository

import com.andres.rangel.vitalyn.authentication.data.mapper.toDomain
import com.andres.rangel.vitalyn.authentication.data.remote.datasource.ILoginRemoteDataSource
import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginRequestDto
import com.andres.rangel.vitalyn.authentication.domain.model.User
import com.andres.rangel.vitalyn.authentication.domain.repository.ILoginRepository

class LoginRepositoryImpl(
    private val remoteDataSource: ILoginRemoteDataSource
) : ILoginRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = remoteDataSource.login(LoginRequestDto(email, password))
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}