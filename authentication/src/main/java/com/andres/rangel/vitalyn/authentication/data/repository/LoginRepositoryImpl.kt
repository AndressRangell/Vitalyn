package com.andres.rangel.vitalyn.authentication.data.repository

import com.andres.rangel.vitalyn.authentication.data.mapper.toDomain
import com.andres.rangel.vitalyn.authentication.data.remote.datasource.LoginRemoteDataSource
import com.andres.rangel.vitalyn.authentication.data.remote.dto.LoginRequestDto
import com.andres.rangel.vitalyn.authentication.domain.model.User
import com.andres.rangel.vitalyn.authentication.domain.repository.LoginRepository
import retrofit2.HttpException

class LoginRepositoryImpl(
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = remoteDataSource.login(LoginRequestDto(email, password))!!

            Result.success(response.toDomain())
        } catch (e: HttpException) {
            if (e.code() == 401) {
                Result.failure(CredentialsInvalidException())
            } else {
                Result.failure(e)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class CredentialsInvalidException : Exception("Credenciales incorrectas")
