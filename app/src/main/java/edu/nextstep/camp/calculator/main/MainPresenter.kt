package edu.nextstep.camp.calculator.main

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private var historyStorage: ExpressionHistoryStorage = ExpressionHistoryStorage()
) : MainContract.Presenter {


    override fun addOperand(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun addOperator(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun expressionCalculate() {
        val result = calculator.calculate(expression.toString())
        if (result != null) {
            view.showResult(result)
            historyStorage += ExpressionHistory(expression, result)
        } else {
            view.showIncompleteExpression()
        }
    }

    override fun requestHistory(visible: Boolean) {
        if(visible) {
            view.showHistory(historyStorage.getHistory())
        } else {
            view.hideHistory()
        }
    }
}