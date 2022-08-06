package edu.nextstep.camp.calculator.domain

class ExpressionHistoryUseCase(
    private val expressions: MutableList<ExpressionHistory> = mutableListOf()
) {
    fun saveHistory(expression: ExpressionHistory) = expressions.add(expression)
    fun getHistories() = expressions
}