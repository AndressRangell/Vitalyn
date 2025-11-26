package com.andres.rangel.vitalyn.hydration.ui.model

import com.andres.rangel.vitalyn.hydration.domain.util.enum.DrinkType

data class UserHydrationLogUI(
    val drinkType: DrinkType,
    val amount: Int,
    val formattedTime: String
)
