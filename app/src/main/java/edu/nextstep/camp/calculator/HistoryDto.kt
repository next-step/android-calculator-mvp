package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.History

data class HistoryDto(val expression: String, val result: String) {
    constructor(history: History) : this(history.rawExpression, "= ${history.result.toString()}")
}
