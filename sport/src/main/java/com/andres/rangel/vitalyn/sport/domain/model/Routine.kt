package com.andres.rangel.vitalyn.sport.domain.model

data class Routine(
    val title: String,
    val category: String,
    val exerciseList: List<Exercise>,
    val duration: Int,
    val progress: Int,
    val image: Int?
)