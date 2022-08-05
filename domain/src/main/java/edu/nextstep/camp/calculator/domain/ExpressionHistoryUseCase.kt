package edu.nextstep.camp.calculator.domain

class ExpressionHistoryUseCase(
    private val expressions: MutableList<ExpressionHistoryItem> = mutableListOf()
) {
    fun saveHistory(expression: ExpressionHistoryItem) = expressions.add(expression)
    fun getHistories() = expressions
}