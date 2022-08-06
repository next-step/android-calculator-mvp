package edu.nextstep.camp.calculator.domain

class ExpressionHistoryStorage(
    private val expressions: MutableList<ExpressionHistory> = mutableListOf()
) {
    fun saveHistory(expression: ExpressionHistory) = expressions.add(expression)
    fun getHistories(): List<ExpressionHistory> = expressions
}