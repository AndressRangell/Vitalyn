package com.andres.rangel.vitalyn.authentication.domain.util.validation

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val message: String) : ValidationResult()
}