package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operand
import edu.nextstep.camp.calculator.domain.StringExpressionState

interface MainContract {

    interface Presenter {

        fun addElement(rawExpression: String, operand: Operand)
    }

    interface View {

        fun setExpression(state: StringExpressionState)
    }
}

