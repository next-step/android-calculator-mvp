package edu.nextstep.camp.calculator

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showExpression(expression: String)
        fun showToast()
    }

    interface Presenter{
        fun appendOperator()
        fun appendOperand()
        fun removeLast()
        fun evaluate()

    }
}

interface BaseView<T> {
    var presenter: T
}