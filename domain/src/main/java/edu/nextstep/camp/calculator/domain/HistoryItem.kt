package edu.nextstep.camp.calculator.domain

internal data class HistoryItem(val formula: Expression, val result: Expression) {
    override fun toString(): String {
        return "${formula}$NEW_LINE$EQUAL$SPACE${result}"
    }
}
