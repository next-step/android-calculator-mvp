package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.CalculationMemory
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class CalculatorPresenter(
    private val view: CalculatorContract.View,
    private var expression: Expression = Expression.EMPTY,
    private val calculationMemory: CalculationMemory = CalculationMemory()
) : CalculatorContract.Presenter {
    private val calculator = Calculator()

    override fun addExpressionElement(element: Int) {
        expression += element
        view.refreshExpression(expression)
    }

    override fun addExpressionElement(element: Operator) {
        if (expression.isEmpty()) return
        expression += element
        view.refreshExpression(expression)
    }

    override fun removeLastExpressionElement() {
        if (expression.isEmpty()) return
        expression = expression.removeLast()
        view.refreshExpression(expression)
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.notifyInCompleteExpression()
            return
        }
        calculationMemory.addRecord(expression, result)
        expression = Expression.EMPTY + result
        view.refreshCalculationMemory(calculationMemory.records)
        view.refreshExpression(expression)
    }

    override fun toggleCalculationMemory(isVisible: Boolean) {
        if (isVisible) {
            view.invisibleCalculationMemory()
            return
        }
        view.visibleCalculationMemory()
    }
}
