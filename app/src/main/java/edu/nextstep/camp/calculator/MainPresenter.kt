package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private val expressionHistoryUseCase: ExpressionHistoryUseCase = ExpressionHistoryUseCase()
) : MainContract.Presenter {

    private var isShowHistory: Boolean = false

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun removeLastFromExpression() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpressionError()
            return
        }
        expressionHistoryUseCase.saveHistory(
            ExpressionHistoryItem(
                expression = expression.toString(),
                result = result
            )
        )
        expression = Expression.EMPTY + result
        view.showExpression(expression)
    }

    override fun toggleExpressionHistory() {
        isShowHistory = if (!isShowHistory) {
            view.openHistory(expressionHistoryUseCase.getHistories())
            true
        } else {
            view.closeHistory()
            false
        }
    }
}
