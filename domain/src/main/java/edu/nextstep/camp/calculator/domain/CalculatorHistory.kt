package edu.nextstep.camp.calculator.domain

class CalculatorHistory(
    private val historyList: MutableList<History> = mutableListOf()
) {
    fun addCalculatorHistory(history: History) {
        historyList.add(history)
    }

    fun getCalculatorHistory() = historyList.toList()
}