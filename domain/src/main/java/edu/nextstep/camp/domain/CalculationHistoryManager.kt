package edu.nextstep.camp.domain

class CalculationHistoryManager {
    private var historyIdCount: Long = 0
    private val _calculationHistoryList = mutableListOf<CalculationHistory>()
    val calculationHistoryList: List<CalculationHistory>
        get() = listOf(*(_calculationHistoryList.toTypedArray()))

    fun saveCalculationHistory(calculationExpression: Expression, calculationResult: Int) {
        _calculationHistoryList.add(CalculationHistory(historyIdCount++, calculationExpression, calculationResult))
    }
}