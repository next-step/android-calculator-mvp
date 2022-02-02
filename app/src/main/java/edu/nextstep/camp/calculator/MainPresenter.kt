package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Memory

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private var expression = Expression.EMPTY

    override fun addOperand(rawOperand: String) {
        expression = expression.addOperand(rawOperand)
        view.refreshExpressionView(expression.rawExpression)
    }

    override fun addOperator(rawOperator: String) {
        expression = expression.addOperator(rawOperator)
        view.refreshExpressionView(expression.rawExpression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.refreshExpressionView(expression.rawExpression)
    }

    override fun calculate() {
        try {
            val result = expression.getResult().toString()
            view.refreshExpressionView(result)
            addMemory(expression.rawExpression, result)
        } catch (e: IllegalArgumentException) {
            view.showErrorToast()
        }
    }

    override fun toggleMemory() {
        view.toggleMemoryView()
    }

    private fun addMemory(expression: String, result: String) {
        view.addMemory(Memory(expression, result))
    }
}