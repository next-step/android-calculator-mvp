package edu.nextstep.camp.calculator

import edu.nextstep.camp.domain.CalculationHistoryManager
import edu.nextstep.camp.domain.Calculator
import edu.nextstep.camp.domain.Expression
import edu.nextstep.camp.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator,
    private val calculationHistoryManager: CalculationHistoryManager
): MainContract.Presenter {

    private var expression = Expression.EMPTY
    private var uiMode = CalculatorUiMode.CALCULATOR

    override fun addNumberToExpression(number: Int) {
        expression += number

        showCurrentExpression()
    }

    override fun addOperatorToExpression(operator: Operator) {
        expression += operator

        showCurrentExpression()
    }

    override fun removeLastToken() {
        expression = expression.removeLast()

        showCurrentExpression()
    }

    override fun calculateCurrentExpression() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showErrorMessage(IncompleteExpressionException())
            return
        }

        calculationHistoryManager.saveCalculationHistory(expression, result)

        expression = Expression.EMPTY + result

        view.showResult(result)
    }

    override fun toggleUiBetweenCalculatorOrHistory() {
        uiMode = when (uiMode) {
            CalculatorUiMode.CALCULATION_HISTORY -> CalculatorUiMode.CALCULATOR
            CalculatorUiMode.CALCULATOR -> CalculatorUiMode.CALCULATION_HISTORY
        }

        showCurrentUi()
    }

    private fun showCurrentUi() {
        when (uiMode) {
            CalculatorUiMode.CALCULATOR -> view.showCalculatorUi()
            CalculatorUiMode.CALCULATION_HISTORY -> view.showCalculationHistoryList(calculationHistoryManager.getCalculationHistoryList())
        }
    }

    private fun showCurrentExpression() {
        view.showExpression(expression)
    }
}