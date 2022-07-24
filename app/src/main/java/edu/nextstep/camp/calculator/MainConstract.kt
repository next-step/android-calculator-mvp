package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainConstract {
    interface Presenter {
        fun addToExpression(expression: Operator)
        fun addToExpression(expression: Int)
        fun removeLast()
        fun calculate()
    }

    interface View {
        fun showExpression(expression: Expression)
        fun showToast(resId: Int)
    }
}