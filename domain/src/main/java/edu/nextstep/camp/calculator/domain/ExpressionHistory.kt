package edu.nextstep.camp.calculator.domain

class ExpressionHistory {

    private val histories = mutableListOf<ExpressionHistoryItem>()

    fun save(rawExpression: String, result: Int) {
        histories.add(ExpressionHistoryItem(rawExpression, result))
    }

    fun loadHistories(): List<ExpressionHistoryItem> {
        return histories
    }
}