package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression.EMPTY,
) : MainContract.Presenter {
    private val calculator = Calculator()

    override fun input(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun input(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun calculate() {
        when (val calculateResult = calculator.calculate(expression.toString())) {
            null -> { onFailCalculate() }
            else -> onSuccessCalculate(calculateResult)
        }
    }

    private fun onSuccessCalculate(result: Int) {
        initExpression(result)
    }

    private fun onFailCalculate() {
        view.onFailCalculate()
    }

    override fun initExpression(result : Int) {
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }
}
