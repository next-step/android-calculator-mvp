package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY
): MainContract.Presenter {

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun calculate() {
        val calculatedValue = calculator.calculate(expression.toString())
        if(calculatedValue == null) {
            view.onError(IllegalArgumentException("유효하지 않은 수식입니다."))
            return
        }
        expression = Expression.EMPTY + calculatedValue
        view.showExpression(expression)
    }

    override fun delete() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

}