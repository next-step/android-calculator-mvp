package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.ExpressionHistory
import edu.nextstep.camp.calculator.domain.MemoryExpressionHistory
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private val historySaver: ExpressionHistory = MemoryExpressionHistory(),
) : MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression.EMPTY
    private var isHistoryDisplayed = false

    override fun addToExpression(operand: Int) {
        expression += operand
        view.showExpression(expression.toString())
    }

    override fun addToExpression(operator: Operator) {
        expression += operator
        view.showExpression(expression.toString())
    }

    override fun removeLastFromExpression() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())

        if (result == null) {
            view.showCalculationFailMessage()
            return
        }

        historySaver.save(expression.toString(), result)
        expression = Expression.newInstance(result)
        view.showExpression(expression.toString())
    }

    override fun toggleExpressionHistory() {
        isHistoryDisplayed = !isHistoryDisplayed
        if (isHistoryDisplayed) {
            historySaver.loadHistories()
                .map { (rawExp, result) -> CalculationHistoryItem(rawExp, result) }
                .also { view.openCalculationHistories(it) }
        } else {
            view.closeCalculationHistories()
        }
    }
}
