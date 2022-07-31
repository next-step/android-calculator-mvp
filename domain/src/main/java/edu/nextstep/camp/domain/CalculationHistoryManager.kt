package edu.nextstep.camp.domain

class CalculationHistoryManager {
    private val calculationHistoryList = mutableListOf<CalculationHistory>()

    fun saveCalculationHistory(calculationExpression: Expression, calculationResult: Int) {
        calculationHistoryList.add(CalculationHistory(calculationExpression, calculationResult))
    }

    fun getCalculationHistoryList(): List<CalculationHistory> {
        return calculationHistoryList
    }
}