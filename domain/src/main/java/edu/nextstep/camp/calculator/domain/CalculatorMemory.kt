package edu.nextstep.camp.calculator.domain

class CalculatorMemory(
    calculatorRecords: List<ExpressionRecord> = listOf()
) {
    private val expressionRecords: MutableList<ExpressionRecord> = calculatorRecords.toMutableList()

    fun saveExpressionRecord(vararg records: ExpressionRecord) {
        expressionRecords.addAll(records)
    }

    fun loadExpressionRecords(): List<ExpressionRecord> {
        return expressionRecords.toList()
    }

    override fun toString(): String {
        return expressionRecords.joinToString("\n")
    }
}