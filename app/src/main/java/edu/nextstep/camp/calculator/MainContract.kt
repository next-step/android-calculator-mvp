package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View {
        var presenter: Presenter
        fun showExpression(expression: Expression)
        fun showIncompleteExpression()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLast()
        fun calculate()
    }
}
