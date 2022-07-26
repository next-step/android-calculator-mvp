package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.CalculatorMemory
import edu.nextstep.camp.calculator.domain.Operator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.ExpressionRecord
import edu.nextstep.camp.calculator.domain.Calculator


class MainPresenter(
    private val view: MainConstract.View,
    private var expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator(),
    private val calculatorMemory: CalculatorMemory = CalculatorMemory(),
    private var isRecordsMode: Boolean = false,
) : MainConstract.Presenter {

    override fun addToExpression(expression: Operator) {
        this.expression += expression
        hideExpressionRecords()
        showExpressionToView()
    }

    override fun addToExpression(expression: Int) {
        this.expression += expression
        hideExpressionRecords()
        showExpressionToView()
    }

    override fun removeLast() {
        this.expression = expression.removeLast()
        hideExpressionRecords()
        showExpressionToView()
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.failedCalculate()
            return
        }

        calculatorMemory.saveExpressionRecord(ExpressionRecord(expression, result))
        expression = Expression.EMPTY + result
        view.succeedCalculate(expression)
    }

    override fun toggleDisplayRecords() {
        when (isRecordsMode) {
            true -> {
                view.hideExpressionRecords()
                view.showExpression(expression)
            }
            false -> view.showExpressionRecords(calculatorMemory.loadExpressionRecords())
        }

        isRecordsMode = !isRecordsMode
    }

    private fun hideExpressionRecords() {
        if (isRecordsMode) {
            isRecordsMode = false
            view.hideExpressionRecords()
        }
    }

    private fun showExpressionToView() {
        view.showExpression(this.expression)
    }
}