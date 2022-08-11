package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View
): MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression.EMPTY
    private val history = History()
    private var isHistoryShow = false

    override fun appendOperand(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun appendOperator(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun deleteLast() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showErrorToast()
            return
        }

        history.addHistory(expression, result)
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }

    override fun toggleHistoryViewMode() {
        if (isHistoryShow) {
            isHistoryShow = false
            view.hideHistoryView()
        } else {
            isHistoryShow = true
            view.showHistoryView(history.getHistories())
        }
    }
}