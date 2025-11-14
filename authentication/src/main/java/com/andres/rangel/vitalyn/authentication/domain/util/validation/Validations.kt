package com.andres.rangel.vitalyn.authentication.domain.util.validation

object EmailValidation {
    private val EMAIL_REGEX = Regex(
        pattern = "[a-zA-Z0-9+._%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    )

    fun isValidEmail(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult.Invalid("El correo es obligatorio")
            !EMAIL_REGEX.matches(email) -> ValidationResult.Invalid("Formato de correo electrónico inválido")
            else -> ValidationResult.Valid
        }
    }
}

object PasswordValidation {
    private val MIN_LENGTH = 8

    fun isValidPassword(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult.Invalid("La contraseña es obligatoria")
            password.length < MIN_LENGTH -> ValidationResult.Invalid("La contraseña debe tener mínino $MIN_LENGTH caracteres")
            !password.any { it.isDigit() } -> ValidationResult.Invalid("La contraseña debe tener mínimo un número")
            !password.any { !it.isLetterOrDigit() && !it.isWhitespace() } -> ValidationResult.Invalid(
                "La contraseña debe tener mínimo un caracter especial"
            )
            else -> ValidationResult.Valid
        }
    }
}