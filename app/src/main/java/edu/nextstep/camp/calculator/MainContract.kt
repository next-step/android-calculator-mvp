package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(rawExpression: String)
        fun showCalculationHistories(histories: String)
        fun showCalculationFailMessage()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLastFromExpression()
        fun calculateExpression()
        fun searchExpressionHistory()
    }
}
