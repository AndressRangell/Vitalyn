package com.andres.rangel.vitalyn.authentication.domain.repository

import com.andres.rangel.vitalyn.authentication.domain.model.User

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<User>
}