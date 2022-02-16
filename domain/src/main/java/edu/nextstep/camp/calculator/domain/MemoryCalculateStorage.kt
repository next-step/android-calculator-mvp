package edu.nextstep.camp.calculator.domain

class MemoryCalculateStorage: CalculateStorage {
    private val _history: MutableList<String> = mutableListOf()

    override val history: List<String>
        get() = _history

    override fun save(formula: Expression, result: Expression) {
        _history.add("$formula\n= $result")
    }
}