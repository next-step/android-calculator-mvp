package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression

interface MainContract {

    interface MainPresenter {

        fun addOperand(): Expression

        fun addOperator(): Expression

        fun deleteLast(): Expression

        fun calculate(): Expression

    }

    interface MainView {

        fun showCurrentExpression(expression: Expression)

        fun showCalculateValue(expression: Expression)

    }

}