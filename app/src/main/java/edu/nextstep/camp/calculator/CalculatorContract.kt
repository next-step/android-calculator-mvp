package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.common.BaseView

interface CalculatorContract {
    interface View : BaseView<Presenter> {
        fun refreshExpression(expression: Expression)
    }

    interface Presenter {
        fun addExpressionElement(element: Int)
        fun addExpressionElement(element: Operator)
        fun removeLastExpressionElement()
    }
}
