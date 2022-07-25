package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {

    interface Presenter {

        fun addOperand(operand: Int)

        fun addOperator(operator: Operator)

        fun dropLast()

        fun calculate()

    }

    interface View {

        fun showCurrentExpression(expression: Expression)

        fun showCalculateValue(expression: Expression)

    }

}