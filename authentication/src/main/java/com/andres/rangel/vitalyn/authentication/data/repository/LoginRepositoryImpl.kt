package com.andres.rangel.vitalyn.authentication.data.repository

import com.andres.rangel.vitalyn.authentication.domain.model.User
import com.andres.rangel.vitalyn.authentication.domain.repository.ILoginRepository
import com.andres.rangel.vitalyn.authentication.domain.util.enum.UserState
import kotlinx.coroutines.delay

class LoginRepositoryImpl : ILoginRepository {

    companion object {
        private const val MOCK_EMAIL = "[EMAIL]"
        private const val MOCK_PASSWORD = "[PASSWORD]"
    }

    override suspend fun login(email: String, password: String): Result<User> {
        delay(2000)

        return if (email == MOCK_EMAIL && password == MOCK_PASSWORD) {
            Result.success(
                User(
                    email = MOCK_EMAIL,
                    name = "Administrador",
                    state = UserState.ACTIVE
                )
            )
        } else {
            Result.failure(Throwable("Credenciales incorrectas"))
        }
    }
}