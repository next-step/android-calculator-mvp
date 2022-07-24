package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(private val view: MainConstract.View) : MainConstract.Presenter {
    private var expression = Expression.EMPTY
    private val calculator = Calculator()

    override fun addToExpression(expression: Operator) {
        this.expression += expression
        view.showExpression(this.expression)
    }

    override fun addToExpression(expression: Int) {
        this.expression += expression
        view.showExpression(this.expression)
    }

    override fun removeLast() {
        this.expression = expression.removeLast()
        view.showExpression(this.expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.showToast(R.string.incomplete_expression)
            return
        } else {
            expression = Expression.EMPTY + result
            view.showExpression(this.expression)
        }
    }
}