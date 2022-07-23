package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator,
    private var expression: Expression,
) : MainContract.Presenter {

    private var isShowingHistory = false

    override fun enterNumber(number: Int) {
        if (isShowingHistory) return

        expression += number
        view.showExpression(expression.toString())
    }

    override fun enterOperator(operator: Operator) {
        if (isShowingHistory) return

        expression += operator
        view.showExpression(expression.toString())
    }

    override fun calculate() {
        if (isShowingHistory) return

        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.showIncomplete()
            return
        }

        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }

    override fun removeLast() {
        if (isShowingHistory) return

        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun toggleHistory() {
        isShowingHistory = !isShowingHistory

        if (isShowingHistory) {
            view.showHistory(calculator.historyList)
        } else {
            view.hideHistory()
        }
    }
}
