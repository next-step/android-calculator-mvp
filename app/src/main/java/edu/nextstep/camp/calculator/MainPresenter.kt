package edu.nextstep.camp.calculator

import android.util.Log
import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(
    private val view: MainContract.View,
    private var expression: Expression = Expression(),
    private val calculator: Calculator = Calculator(),
    private var histories: List<History> = listOf()
) : MainContract.Presenter {

    private var expression = Expression()
    private val calculator = Calculator()

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
            histories = mutableListOf<History>().apply {
                addAll(histories)
                add(History(expression, result.toString()))
            }
            expression = Expression.EMPTY + result
            view.showCalculateValue(expression)
            return
        }

        view.showExpressionErrorToast()
    }

    override fun toggleHistory(isShow: Boolean) {
        if (isShow) {
            view.hideHistory()
        } else {
            Log.e("historyTest", "@@ $histories")
            view.showHistory(histories)
        }
    }

}