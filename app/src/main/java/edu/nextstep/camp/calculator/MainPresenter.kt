package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.Operator

class MainPresenter(view: MainContract.View) : MainContract.Presenter {
    private var expression: Expression = Expression()
    val calculator: Calculator = Calculator()
    var view = view

    override fun clickOperand(operand: Int) {
        expression = expression.plus(operand)
        view.showExpression(expression.toString())
    }

    override fun clickOperator(operator: Operator) {
        expression = expression.plus(operator)
        view.showExpression(expression.toString())
    }

    override fun clickEqual() {
        val result = calculator.calculate(expression.toString())
            ?: return view.showToast("완성되지 않은 수식입니다.")
        expression = Expression.EMPTY + result
        view.showExpression(expression.toString())
    }

    override fun clickDelete() {
        expression = expression.removeLast()
        view.showExpression(expression.toString())
    }
}