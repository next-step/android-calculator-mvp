package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private var calculatorHistory: CalculatorHistory = CalculatorHistory()
) : MainContract.Presenter {

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpression()
            return
        }
        calculatorHistory.addCalculatorHistory(History(expression.toString(), result))
        expression = Expression.EMPTY + result
        view.showExpression(expression)
    }

    override fun toggleHistory(isHistoryVisible: Boolean) {
        when (isHistoryVisible) {
            true -> view.hideHistory()
            false -> view.showHistory(calculatorHistory.getCalculatorHistory())
        }
    }
}
