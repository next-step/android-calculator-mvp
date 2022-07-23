package edu.nextstep.camp.calculator.domain

interface ExpressionHistorySaver {
    fun save(rawExpression: String, rawResult: String)
    fun loadHistories(): List<Pair<String, Int>>
}