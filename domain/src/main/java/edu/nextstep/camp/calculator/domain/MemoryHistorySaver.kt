package edu.nextstep.camp.calculator.domain

class MemoryHistorySaver : ExpressionHistorySaver {

    private val histories = mutableListOf<Pair<String, Int>>()

    override fun save(rawExpression: String, rawResult: String) {
        histories.add(rawExpression to rawResult.toInt())
    }

    override fun loadHistories(): List<Pair<String, Int>> {
        return histories
    }
}
