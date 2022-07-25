package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.StringExpressionState

interface MainContract {

    interface Presenter {

        fun addElement(rawExpression: String, operator: Operator)

        fun addElement(rawExpression: String, operand: Operand)

        fun removeElement(rawExpression: String)
    }

    interface View {

        fun setExpression(state: StringExpressionState)
    }
}

