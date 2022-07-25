package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(result: String)
        fun showToast(message: String)
    }

    interface Presenter {
        fun addToExpression(operator: Operator)
        fun addToExpression(operand : Int)
        fun calculateExpression()
        fun removeLastFromExpression()
    }
}