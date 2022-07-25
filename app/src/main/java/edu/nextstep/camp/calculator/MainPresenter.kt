package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var expression: Expression = Expression()
    val calculator: Calculator = Calculator()

    override fun addToExpression(operand: Int) {
        expression = expression.plus(operand)
        view.showExpression(expression.toString())
    }

    override fun addToExpression(operator: Operator) {
        expression = expression.plus(operator)
        view.showExpression(expression.toString())
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())
            ?: return view.showError("완성되지 않은 수식입니다.")
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }

    override fun removeLastFromExpression() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }
}