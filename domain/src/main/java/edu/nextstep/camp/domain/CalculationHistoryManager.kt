package edu.nextstep.camp.domain

class CalculationHistoryManager {
    private var historyIdCount: Long = 0
    private val calculationHistoryList = mutableListOf<CalculationHistory>()

    fun saveCalculationHistory(calculationExpression: Expression, calculationResult: Int) {
        calculationHistoryList.add(CalculationHistory(historyIdCount++, calculationExpression, calculationResult))
    }

    fun getCalculationHistoryList(): List<CalculationHistory> {
        return listOf(*(calculationHistoryList.toTypedArray()))
    }
}