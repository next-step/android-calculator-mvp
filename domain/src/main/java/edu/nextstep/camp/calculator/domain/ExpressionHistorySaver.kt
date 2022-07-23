package edu.nextstep.camp.calculator.domain

interface ExpressionHistorySaver {
    fun save(rawExpression: String, result: Int)
    fun loadHistories(): List<Pair<String, Int>>
}