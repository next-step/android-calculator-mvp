package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.StringExpressionState

data class Record(
    val state: StringExpressionState,
    val result: Operand
)
