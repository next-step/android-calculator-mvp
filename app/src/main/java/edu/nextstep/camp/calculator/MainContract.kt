package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View {

        fun showExpression(expression: Expression)
    }

    interface Presenter {
        val expression: Expression

        fun formatExpression(number: Int)

        fun formatExpression(operator: Operator)
    }

}
