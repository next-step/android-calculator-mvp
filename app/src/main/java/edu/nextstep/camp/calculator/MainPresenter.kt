package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.CalculatorMemory
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainConstract.View,
    private var expression: Expression = Expression.EMPTY,
    private val calculator: Calculator = Calculator(),
    private val calculatorMemory: CalculatorMemory = CalculatorMemory(),
    private var isRecordsMode: Boolean = false,
) : MainConstract.Presenter {

    override fun addToExpression(expression: Operator) {
        this.expression += expression
        showExpressionToView()
    }

    override fun addToExpression(expression: Int) {
        this.expression += expression
        showExpressionToView()
    }

    override fun removeLast() {
        this.expression = expression.removeLast()
        showExpressionToView()
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.failedCalculate()
            return
        }

        calculatorMemory.saveExpressionRecord(expression, result)
        expression = Expression.EMPTY + result
        showExpressionToView()
    }

    override fun toggleDisplayRecords() {
        when (isRecordsMode) {
            true -> view.disableExpression(this.expression)
            false -> view.showExpression(calculatorMemory.loadExpressionRecords())
        }

        isRecordsMode = !isRecordsMode
    }

    private fun showExpressionToView() {
        if (isRecordsMode) {
            isRecordsMode = false
            view.disableExpression(this.expression)
        }

        view.succeedCalculate(this.expression)
    }
}