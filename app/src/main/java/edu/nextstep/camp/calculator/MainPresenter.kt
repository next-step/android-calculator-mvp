package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY

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

    override fun calculate(): Int? {
        return calculator.calculate(expression.toString())
    }

    override fun initExpression(result : Int) {
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }
}
