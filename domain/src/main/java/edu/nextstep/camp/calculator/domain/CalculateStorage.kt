package edu.nextstep.camp.calculator.domain

interface CalculateStorage {
    val history: List<String>
    fun save(formula: Expression, result: Expression)
}