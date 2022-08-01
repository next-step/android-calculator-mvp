package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.*

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression(),
    private val calculator: Calculator = Calculator(),
    private var histories: Histories = Histories.of(emptyList()),
) : MainContract.Presenter {

    private var isShowHistories = false

    override fun addOperand(operand: Int) {
        expression += operand
        view.showCurrentExpression(expression)
    }

    override fun addOperator(operator: Operator) {
        expression += operator
        view.showCurrentExpression(expression)
    }

    override fun dropLast() {
        expression = expression.dropLast()
        view.showCurrentExpression(expression)
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result != null) {
            histories += History(expression, result.toString())
            expression = Expression.EMPTY + result
            view.showCalculateValue(expression)
            return
        }

        view.showExpressionErrorToast()
    }

    override fun toggleHistory() {
        if (isShowHistories) {
            view.hideHistory()
        } else {
            view.showHistory(histories.toList())
        }

        isShowHistories = !isShowHistories
    }

}