package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression

interface MainContract {

    interface Presenter {

        fun addOperand()

        fun addOperator()

        fun deleteLast()

        fun calculate()

    }

    interface View {

        fun showCurrentExpression(expression: Expression)

        fun showCalculateValue(expression: Expression)

    }

}