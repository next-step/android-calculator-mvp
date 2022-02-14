package edu.nextstep.camp.calculator.domain

interface CalculateStorage {
    fun save(formula: Expression, result: Expression)
    fun getAll(): List<String>
    fun clear()
}