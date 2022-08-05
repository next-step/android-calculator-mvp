package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.ExpressionHistoryItem
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: Expression)
        fun showIncompleteExpressionError()
        fun openHistory(expressions: List<ExpressionHistoryItem>)
        fun closeHistory()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLastFromExpression()
        fun calculateExpression()
        fun toggleExpressionHistory(isShow: Boolean)
    }
}
