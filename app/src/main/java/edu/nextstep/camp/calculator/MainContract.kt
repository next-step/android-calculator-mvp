package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Operator

interface MainContract {
    interface View {
        fun showExpression(expression: String)
        fun showExpressionError()
    }

    interface Presenter {
        fun inputNumber(number: Int)
        fun inputPlus()
        fun inputMinus()
        fun inputMultiply()
        fun inputDivide()
        fun deleteLast()
        fun calculate()
    }
}
