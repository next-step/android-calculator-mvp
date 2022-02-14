package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {
    private var expression = Expression.EMPTY

    private val calculator = Calculator()

    private val calculatorMemory = mutableListOf<Pair<String, Int>>()

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
        view.hideMemoryList()
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
        view.hideMemoryList()
    }

    override fun removeLastToExpression() {
        expression = expression.removeLast()
        view.showExpression(expression)
        view.hideMemoryList()
    }

    override fun calculateToExpression() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showToast(R.string.incomplete_expression)
            return
        }
        saveCalculatorInMemory(expression.toString(), result)
        expression = Expression.EMPTY + result
        view.showExpression(expression)
        view.hideMemoryList()
    }

    override fun checkMemoryListVisible() {
        if (view.getMemoryListVisible()) {
            view.hideMemoryList()
            view.showExpression(expression)
        } else {
            view.showMemoryList(calculatorMemory)
            view.hideExpression()
        }
    }

    private fun saveCalculatorInMemory(expression: String, result: Int) {
        calculatorMemory.add(Pair(expression, result))
    }
}