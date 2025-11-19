package com.andres.rangel.vitalyn.authentication.domain.util.enum

enum class UserState(val id: Int, val label: String) {
    UNDEFINED(id = -1, label = "Indefinido"),
    INACTIVE(id = 0, label = "Inactivo"),
    ACTIVE(id = 1, label = "Activo");

    companion object {
        fun fromId(id: Int): UserState? =
            entries.find { it.id == id }
    }
}