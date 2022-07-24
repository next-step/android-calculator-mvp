package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(result: String)
        fun showToast(message: String)
    }

    interface Presenter {
        var expression : Expression
        var operator : Operator
        var calculator : Calculator
        fun clickOperator(operator: Operator)
        fun clickOperand(operand : Int)
        fun clickEqual()
        fun clickDelete()
    }
}