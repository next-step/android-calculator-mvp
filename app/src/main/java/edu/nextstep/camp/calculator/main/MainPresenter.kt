package edu.nextstep.camp.calculator.main

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun addOperand(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun addOperator(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun removeLast() {
        expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun expressionCalculate() {
        val result = calculator.calculate(expression.toString())
        view.showResult(result)
        if (result != null) {
            expression = Expression.EMPTY + result
        }
    }
}