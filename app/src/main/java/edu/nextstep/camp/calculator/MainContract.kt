package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface Presenter {

        fun addOperand(operand: Int)

        fun addOperator(operator: Operator)

        fun dropLast()

        fun calculate()

        fun toggleHistory(isShow: Boolean)

    }

    interface View {

        fun showCurrentExpression(expression: Expression)

        fun showCalculateValue(expression: Expression)

        fun showExpressionErrorToast()

        fun showHistory(histories: List<History>)

        fun hideHistory()

    }

}