package edu.nextstep.camp.calculator.domain

class ExpressionHistory {

    private val histories = mutableListOf<Pair<String, Int>>()

    fun save(rawExpression: String, result: Int) {
        histories.add(rawExpression to result)
    }

    fun loadHistories(): List<Pair<String, Int>> {
        return histories
    }
}
