package edu.nextstep.camp.calculator

interface MainContract {
    interface View {
        fun showExpression(expression: String)
        fun showExpressionError()
        fun notifyHistories(histories: List<HistoryDto>)
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
