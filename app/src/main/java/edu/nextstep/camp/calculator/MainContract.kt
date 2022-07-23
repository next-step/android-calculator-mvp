package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(rawExpression: String)
        fun showCalculationFailMessage()

        fun openCalculationHistories(histories: List<CalculationHistoryItem>)
        fun closeCalculationHistories()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLastFromExpression()
        fun calculateExpression()
        fun toggleExpressionHistory()
    }
}
