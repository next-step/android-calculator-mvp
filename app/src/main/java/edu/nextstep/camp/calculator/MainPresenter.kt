package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
) : MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun removeLastFromExpression() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.showCalculationFailMessage()
            return
        }

        expression = Expression.newInstance(result)
        view.showExpression(expression.toString())
    }

    override fun searchExpressionHistory() {
        TODO("Not yet implemented")
    }
}
