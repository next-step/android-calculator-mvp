package edu.nextstep.camp.calculator.domain

interface MainContract {
    interface View {
        fun showExpression(expression: Expression)
        fun showCalculationFailMessage()
    }

    interface Presenter {
        fun addToExpression(operand: Int)
        fun addToExpression(operator: Operator)
        fun removeLast()
        fun calculate()
    }
}

class MainPresenter(
    private val view: MainContract.View,
) : MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun removeLast() {
        expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.showCalculationFailMessage()
            return
        }

        expression = Expression.newInstance(result)
        view.showExpression(expression)
    }
}
