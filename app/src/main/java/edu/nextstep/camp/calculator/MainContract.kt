package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression

interface MainContract {

    interface MainPresenter {

        fun addOperand()

        fun addOperator()

        fun deleteLast()

        fun calculate()

    }

    interface MainView {

        fun showCurrentExpression(expression: Expression)

        fun showCalculateValue(expression: Expression)

    }

}