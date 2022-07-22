package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

/**
 * MainActivity에 대한 Presenter
 * Created by jeongjinhong on 2022. 07. 22..
 */
class MainPresenter(
    private val view: MainContract.View
) : MainContract.Presenter {

    private var expression = Expression.EMPTY
    private val calculator = Calculator()

    override fun addOperand(operand: Int) {
        expression += operand
        view.showExpression(expression)
    }

    override fun addOperator(operator: Operator) {
        expression += operator
        view.showExpression(expression)
    }

    override fun removeLast() {
        expression = expression.removeLast()
        view.showExpression(expression)
    }

    override fun calculateExpression() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showIncompleteExpressionToast()
            return
        }
        expression = Expression.EMPTY + result
        view.showExpression(expression)
    }

}