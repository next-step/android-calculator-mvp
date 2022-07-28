package edu.nextstep.camp.calculator.domain

class ExpressionHistoryStorage(
    private val resultList: List<ExpressionHistory> = listOf()
) {
    operator fun plus(history: ExpressionHistory): ExpressionHistoryStorage {
        return ExpressionHistoryStorage(resultList + history)
    }

    fun getHistory(): List<ExpressionHistory> = resultList
}