package edu.nextstep.camp.calculator.domain

data class HistoryItem(val formula: Expression, val result: Expression) {
    private val historyFormat = "$formula\n= $result"

    override fun toString(): String {
        return historyFormat
    }
}
