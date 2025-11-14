package com.andres.rangel.vitalyn.authentication.domain.repository

import com.andres.rangel.vitalyn.authentication.domain.model.User

interface ILoginRepository {
    suspend fun login(email: String, password: String): Result<User>
}