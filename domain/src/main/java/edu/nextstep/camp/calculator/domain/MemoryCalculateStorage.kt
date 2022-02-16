package edu.nextstep.camp.calculator.domain

class MemoryCalculateStorage: CalculateStorage {
    private val _history: MutableList<HistoryItem> = mutableListOf()
    override val history: List<HistoryItem> get() = _history

    override fun save(formula: Expression, result: Expression) {
        _history.add(HistoryItem(formula, result))
    }
}