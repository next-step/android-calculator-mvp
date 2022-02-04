package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorPresenter(
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {
    private var expression = Expression.EMPTY
    private val isEmptyExpression
        get() = expression == Expression.EMPTY

    override fun addExpressionElement(element: Int) {
        expression += element
        view.refreshExpression(expression)
    }

    override fun addExpressionElement(element: Operator) {
        if (isEmptyExpression) return
        expression += element
        view.refreshExpression(expression)
    }

    override fun removeLastExpressionElement() {
        if (isEmptyExpression) return
        expression = expression.removeLast()
        view.refreshExpression(expression)
    }
}
