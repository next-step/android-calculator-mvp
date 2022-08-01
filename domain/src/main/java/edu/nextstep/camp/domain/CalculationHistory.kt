package edu.nextstep.camp.domain

data class CalculationHistory(
    val id: Long,
    val expression: Expression,
    val result: Int
)
