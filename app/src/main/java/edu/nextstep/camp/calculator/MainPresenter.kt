package edu.nextstep.camp.calculator

import com.joseph.domain.Calculator
import com.joseph.domain.Expression
import com.joseph.domain.Operator

class MainPresenter(val view: MainContract.View): MainContract.Presenter {
    private var expression = Expression.EMPTY
    private val calculator = Calculator()

    override fun addExpression(number: Int) {
        expression += number
        view.displayExpression(expression)
    }

    override fun addExpression(operator: Operator) {
        expression += operator
        view.displayExpression(expression)
    }

    override fun removeAtLastExpression() {
        expression = expression.removeLast()
        view.displayExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpressionToast()
        }
        expression = Expression.EMPTY + result!!
    }
}