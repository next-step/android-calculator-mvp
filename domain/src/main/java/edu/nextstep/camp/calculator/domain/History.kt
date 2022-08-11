package edu.nextstep.camp.calculator.domain

class History {
    private val histories = mutableListOf<HistoryData>()

    fun addHistory(expression: Expression, result: Int) {
        histories.add(HistoryData(expression, result))
    }

    fun getHistories(): List<HistoryData> = histories.toList()
}
