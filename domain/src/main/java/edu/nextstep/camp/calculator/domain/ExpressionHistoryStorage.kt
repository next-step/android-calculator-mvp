package edu.nextstep.camp.calculator.domain

class ExpressionHistoryStorage {
    private val resultList = mutableListOf<ExpressionHistory>()

    fun store(expression: Expression, result: Int) {
        resultList.add(ExpressionHistory(expression, result))
    }

    fun getHistory(): List<ExpressionHistory> = resultList
}