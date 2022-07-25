package edu.nextstep.camp.calculator.domain

interface CalculatorMemory {
    fun loadExpressionRecords(): List<String>
    fun saveExpressionRecord(expression: Expression, result: Int)
}