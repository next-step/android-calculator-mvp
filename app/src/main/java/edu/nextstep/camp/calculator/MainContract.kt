package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringExpressionState

interface MainContract {

    interface Presenter {

        fun addElement(operator: Operator)

        fun addElement(operand: Operand)

        fun removeElement()

        fun calculate()

        fun toggleRecords()
    }

    interface View {

        fun setExpression(state: StringExpressionState)

        fun setCalculationResult(result: Operand)

        fun calculationFailed()

        fun showRecords(records: List<StringExpressionState>)

        fun closeRecords()
    }
}

