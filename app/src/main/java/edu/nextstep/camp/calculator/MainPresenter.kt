package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
): MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun appendOperand(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun appendOperator(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun deleteLast() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showErrorToast()
            return
        }
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }
}