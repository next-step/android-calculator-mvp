package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface View {
        val presenter: Presenter

        fun showExpression(expression: String)
        fun showHistory(history: History)
        fun showIncompleteExpressionToast()
        fun showHistoryDisplay()
        fun showCalculateDisplay()
    }

    interface Presenter {
        val history: History

        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLatest()
        fun calculate()
        fun changeDisplay(isHistoryDisplay: Boolean)
    }
}
