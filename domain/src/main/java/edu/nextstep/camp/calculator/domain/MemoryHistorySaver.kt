package edu.nextstep.camp.calculator.domain

class MemoryHistorySaver : ExpressionHistorySaver {

    private val histories = mutableListOf<Pair<String, Int>>()

    override fun save(rawExpression: String, result: Int) {
        histories.add(rawExpression to result)
    }

    override fun loadHistories(): List<Pair<String, Int>> {
        return histories
    }
}
