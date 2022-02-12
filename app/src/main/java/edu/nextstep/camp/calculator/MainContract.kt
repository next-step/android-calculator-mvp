package edu.nextstep.camp.calculator

interface MainContract {
    interface View {
        fun showExpression(expression: String)
        fun showExpressionError()
    }

    interface Presenter {
        fun addToNumber(number: String)
        fun addToPlus()
        fun addToMinus()
        fun addToMultiply()
        fun addToDivide()
        fun deleteLastInput()
        fun calculate()
    }
}