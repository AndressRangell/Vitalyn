package com.andres.rangel.vitalyn.hydration.domain.model

data class UserHydrationLog(
    val id: Int,
    val amount: Int,
    val drinkTypeId: Int,
    val timestamp: Long
)