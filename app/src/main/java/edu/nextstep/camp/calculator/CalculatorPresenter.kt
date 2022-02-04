package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorPresenter(
    private val view: CalculatorContract.View
) : CalculatorContract.Presenter {
    private var expression = Expression.EMPTY

    override fun addExpressionElement(element: Int) {
        expression += element
        view.refreshExpression(expression)
    }

    override fun addToExpressionElement(element: Operator) {
        if (expression == Expression.EMPTY) return
        expression += element
        view.refreshExpression(expression)
    }
}
