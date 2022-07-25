package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(result: String)
        fun showToast(message: String)
    }

    interface Presenter {
        fun clickOperator(operator: Operator)
        fun clickOperand(operand : Int)
        fun clickEqual()
        fun clickDelete()
    }
}