package edu.nextstep.camp.calculator.main

import edu.nextstep.camp.calculator.domain.ExpressionHistory
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: String)
        fun showIncompleteExpression()
        fun showResult(result: Int)
        fun showHistory(history: List<ExpressionHistory>)
        fun hideHistory()
    }

    interface Presenter {
        fun addOperand(operand: Int)
        fun addOperator(operator: Operator)
        fun removeLast()
        fun expressionCalculate()
        fun toggleHistory()
    }
}