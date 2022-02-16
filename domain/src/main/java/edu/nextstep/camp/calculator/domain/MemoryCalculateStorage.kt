package edu.nextstep.camp.calculator.domain

class MemoryCalculateStorage: CalculateStorage {
    private val _history: MutableList<HistoryItem> = mutableListOf()

    override val history: List<String>
        get() = _history.map { it.toString() }

    override fun save(formula: Expression, result: Expression) {
        _history.add(HistoryItem(formula, result))
    }
}