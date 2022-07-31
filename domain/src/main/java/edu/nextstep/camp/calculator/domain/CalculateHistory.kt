package edu.nextstep.camp.calculator.domain

class CalculateHistory {
    val calculateHistories: List<CalculateResult>
        get() = _calculateHistories.toList()
    private val _calculateHistories = mutableListOf<CalculateResult>()

    fun putCalculateHistory(expression: Expression, result: Int) {
        _calculateHistories.add(CalculateResult(expression = expression, result = result))
    }
}
