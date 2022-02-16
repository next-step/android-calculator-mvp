package edu.nextstep.camp.calculator.domain

interface CalculateStorage {
    val history: List<HistoryItem>
    fun save(formula: Expression, result: Expression)
}