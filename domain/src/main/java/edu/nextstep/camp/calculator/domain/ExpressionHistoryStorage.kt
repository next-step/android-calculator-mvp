package edu.nextstep.camp.calculator.domain

class ExpressionHistoryStorage(
    expressions: List<ExpressionHistory> = emptyList()
) {
    private val _historyExpressions = expressions.toMutableList()
    val historyExpressions get() = _historyExpressions.map { it.copy() }

    fun saveHistory(expression: ExpressionHistory) = _historyExpressions.add(expression)
}