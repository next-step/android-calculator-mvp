package edu.nextstep.camp.domain

import java.util.*

/**
 * Created By Malibin
 * on 8월 12, 2021
 */

data class CalculationHistory(
    val expression: Expression,
    val result: Int,
    val createdDate: Date = Date(),
)
