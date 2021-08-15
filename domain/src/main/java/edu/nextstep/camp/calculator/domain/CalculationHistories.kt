package edu.nextstep.camp.calculator.domain

class CalculationHistories(
    histories: List<CalculationResult> = listOf()
) {
    private val _list: MutableList<CalculationResult> = histories.toMutableList()
    val list: List<CalculationResult>
        get() = _list.toList()

    fun addHistory(resultHistory: CalculationResult) {
        _list.add(resultHistory)
    }
}