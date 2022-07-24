package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainConstract.View,
    private var expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator()
) : MainConstract.Presenter {
    override fun addToExpression(expression: Operator) {
        this.expression += expression
        showExpressionToView()
    }

    override fun addToExpression(expression: Int) {
        this.expression += expression
        showExpressionToView()
    }

    override fun removeLast() {
        this.expression = expression.removeLast()
        showExpressionToView()
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.failedCalculate()
            return
        }

        expression = Expression.EMPTY + result
        showExpressionToView()
    }

    private fun showExpressionToView() {
        view.succeedCalculate(this.expression)
    }
}