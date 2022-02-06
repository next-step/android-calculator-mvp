package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator
import kotlin.math.exp

class MainPresenter(
    private val view: MainContract.View
): MainContract.Presenter {
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

    override fun removeLast() {
        TODO("Not yet implemented")
    }

    override fun evaluate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showToast()
            return
        }
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }
}