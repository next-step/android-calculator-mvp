package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(private val view: MainContract.View): MainContract.Presenter {

    private val calculator = Calculator()
    private var expression = Expression.EMPTY

    override fun inputNumber(number: Int) {
        expression += number

        showCalculateExpression(expression);
    }

    override fun inputOperator(operatorData: Operator) {
        expression += operatorData

        showCalculateExpression(expression)
    }

    override fun removeLastIndexData() {
        expression = expression.removeLast()

        showCalculateExpression(expression)
    }

    override fun calculateInputValue() {

        if (!(expression.isValid())) {
            view.showCompletionOfExpressionDataMessage()
            return
        }

        showCalculateExpression(expression)
    }

    private fun showCalculateExpression(inputExpression: Expression) {
        if (inputExpression.isValid()) view.showCalculateExpression(inputExpression.toString())
    }

}