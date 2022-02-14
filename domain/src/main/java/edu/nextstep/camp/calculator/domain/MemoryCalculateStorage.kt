package edu.nextstep.camp.calculator.domain

class MemoryCalculateStorage(): CalculateStorage {
    private val history: MutableList<String> = mutableListOf()

    override fun save(formula: Expression, result: Expression) {
        history.add("$formula\n= $result")
    }

    override fun getAll(): List<String> {
        return history
    }

    override fun clear() {
        history.clear()
    }
}