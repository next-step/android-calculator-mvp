package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainConstract {
    interface Presenter {
        fun addToExpression(expression: Operator)
        fun addToExpression(expression: Int)
        fun removeLast()
        fun calculate()
        fun toggleDisplayRecords()
    }

    interface View {
        fun succeedCalculate(expression: Expression)
        fun failedCalculate()

        fun showExpression(expression: Expression)
        fun showExpressionRecords(records: List<String>)
        fun hideExpressionRecords()
    }
}