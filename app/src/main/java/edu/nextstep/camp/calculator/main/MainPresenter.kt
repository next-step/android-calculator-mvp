package edu.nextstep.camp.calculator.main

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY
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
        } else {
            view.showIncompleteExpression()
        }
    }
}