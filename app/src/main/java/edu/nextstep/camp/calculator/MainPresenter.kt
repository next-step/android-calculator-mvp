package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Histories
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    private val calculator = Calculator()
    private var expression = Expression.EMPTY
    private var histories = Histories()
    private var calculatorDisabled = false

    override fun inputNumber(number: Int) {
        if (calculatorDisabled) return
        expression += number
        showExpression()
    }

    override fun inputPlus() {
        inputOperator(Operator.Plus)
    }

    override fun inputMinus() {
        inputOperator(Operator.Minus)
    }

    override fun inputMultiply() {
        inputOperator(Operator.Multiply)
    }

    override fun inputDivide() {
        inputOperator(Operator.Divide)
    }

    private fun inputOperator(operator: Operator) {
        if (calculatorDisabled) return
        expression += operator
        showExpression()
    }

    override fun deleteLast() {
        if (calculatorDisabled) return
        expression = expression.removeLast()
        showExpression()
    }

    override fun calculate() {
        if (calculatorDisabled) return
        val rawExpression = expression.toString()
        val result = calculator.calculate(rawExpression)
        if (result == null) {
            view.showExpressionError()
        } else {
            histories += History(rawExpression, result)
            view.notifyHistories(histories.toModel())
            expression = Expression.EMPTY + result
            showExpression()
        }
    }

    override fun toggleCalculator() {
        calculatorDisabled = !calculatorDisabled
        if (calculatorDisabled) {
            view.showHistory()
        } else {
            view.hideHistory()
        }
    }

    private fun showExpression() {
        view.showExpression(expression.toString())
    }

    private fun History.toModel(): HistoryModel = HistoryModel(rawExpression, "= $result")
    private fun Histories.toModel(): List<HistoryModel> = toList().map { it.toModel() }
}
