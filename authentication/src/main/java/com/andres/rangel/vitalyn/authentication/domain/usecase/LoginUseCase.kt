package com.andres.rangel.vitalyn.authentication.domain.usecase

import com.andres.rangel.vitalyn.authentication.domain.model.User
import com.andres.rangel.vitalyn.authentication.domain.repository.ILoginRepository
import com.andres.rangel.vitalyn.authentication.domain.util.validation.EmailValidation
import com.andres.rangel.vitalyn.authentication.domain.util.validation.PasswordValidation
import com.andres.rangel.vitalyn.authentication.domain.util.validation.ValidationResult
import com.andres.rangel.vitalyn.authentication.domain.util.enum.UserState

class LoginUseCase(
    private val loginRepository: ILoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        val emailValidation = EmailValidation.isValidEmail(email)
        if (emailValidation is ValidationResult.Invalid) {
            return Result.failure(Throwable(emailValidation.message))
        }

        val passwordValidation = PasswordValidation.isValidPassword(password)
        if (passwordValidation is ValidationResult.Invalid) {
            return Result.failure(Throwable(passwordValidation.message))
        }

        loginRepository.login(email, password).fold(
            onSuccess = { user ->
                if (user.state == UserState.INACTIVE) {
                    return Result.failure(Throwable("Usuario inactivo"))
                }

                return Result.success(user)
            },
            onFailure = { message ->
                return Result.failure(Throwable(message))
            }
        )
    }
}